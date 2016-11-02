/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liferay.blade.cli;

import aQute.lib.getopt.Arguments;
import aQute.lib.getopt.Description;
import aQute.lib.getopt.Options;
import aQute.lib.io.IO;

import com.liferay.project.templates.ProjectTemplates;
import com.liferay.project.templates.ProjectTemplatesArgs;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

/**
 * @author Gregory Amerson
 * @author Terry Jia
 */
public class InitCommand {

	private final static String _PLUGINS_SDK_7_ZIP = "com.liferay.portal.plugins.sdk-7.0-ga3-20160804222206210.zip";
	private final static String _PLUGINS_SDK_7_NAME = "com.liferay.portal.plugins.sdk-7.0-ga3";
	private final static String _PLUGINS_SDK_7_URL =
		"http://downloads.sourceforge.net/project/lportal/Liferay%20Portal/7.0.2%20GA3/" +
			_PLUGINS_SDK_7_ZIP;

	private final static String[] _SDK_6_GA5_FILES = {
		"app-servers.gradle", "build.gradle", "build-plugins.gradle",
		"build-themes.gradle", "sdk.gradle", "settings.gradle",
		"util.gradle", "versions.gradle" };

	public static final String DESCRIPTION =
		"Initializes a new Liferay workspace";

	public static final String WORKSPACE_VERSION = "1.0.40";

	public InitCommand(blade blade, InitOptions options) throws Exception {
		_blade = blade;
		_options = options;
	}

	public void execute() throws Exception {
		final List<String> args = _options._arguments();

		String name = args.size() > 0 ? args.get(0) : null;

		File destDir = name != null ? new File(
			_blade.getBase(), name) : _blade.getBase();

		trace("Using destDir " + destDir);

		if (destDir.exists() && !destDir.isDirectory()) {
			addError(destDir.getAbsolutePath() + " is not a directory.");
			return;
		}

		if (destDir.exists()) {
			if (isPluginsSDK(destDir)) {
				if (!isPluginsSDK70(destDir)) {
					if (_options.upgrade()) {
						trace(
							"Found plugins-sdk 6.2, upgraded to 7.0, moving contents to new subdirectory " +
								"and initing workspace.");

						File sdk7zip = new File (_blade.getCacheDir(), _PLUGINS_SDK_7_ZIP);

						if (!sdk7zip.exists()) {
							FileUtils.copyURLToFile(new URL(_PLUGINS_SDK_7_URL), sdk7zip);
						}

						File sdk7temp = new File(_blade.getCacheDir(), _PLUGINS_SDK_7_NAME);

						if (!sdk7temp.exists()) {
							try {
								Util.unzip(sdk7zip, sdk7temp, null);
							}
							catch (Exception e) {
								addError("Opening zip file error, "
									+ "please delete zip file: " +
										sdk7zip.getPath());
								return;
							}
						}

						File sdk7 = new File(sdk7temp, "com.liferay.portal.plugins.sdk-7.0");

						for (String fileName : _SDK_6_GA5_FILES) {
							File file = new File(destDir, fileName);

							if (file.exists()) {
								file.delete();
							}
						}

						IO.copy(sdk7, destDir);
					}
					else {
						addError("Unable to run blade init in plugins sdk 6.2, please add -u (--upgrade)"
							+ " if you want to upgrade to 7.0");
						return;
					}
				}

				trace("Found plugins-sdk, moving contents to new subdirectory " +
					"and initing workspace.");

				moveContentsToDir(
					destDir, new File(destDir, "plugins-sdk"), "plugins-sdk");
			}
			else if (destDir.list().length > 0) {
				if (_options.force()) {
					trace("Files found, initing anyways.");
				}
				else {
					addError(
						destDir.getAbsolutePath() +
						" contains files, please move them before continuing " +
							"or use -f (--force) option to init workspace " +
								"anyways.");
					return;
				}
			}
		}

		ProjectTemplatesArgs projectTemplatesArgs = new ProjectTemplatesArgs();

		if (name == null) {
			name = destDir.getName();
		}

		destDir = destDir.getParentFile();

		projectTemplatesArgs.setDestinationDir(destDir);

		if (_options.force() || _options.upgrade()) {
			projectTemplatesArgs.setForce(true);
		}

		projectTemplatesArgs.setName(name);
		projectTemplatesArgs.setTemplate("workspace");

		new ProjectTemplates(projectTemplatesArgs);
	}

	@Arguments(arg = "[name]")
	@Description(DESCRIPTION)
	public interface InitOptions extends Options {

		@Description(
				"create anyway if there are files located at target folder")
		public boolean force();

		@Description("force to refresh workspace template")
		public boolean refresh();

		@Description("upgrade plugins-sdk from 6.2 to 7.0")
		public boolean upgrade();
	}

	private void addError(String msg) {
		_blade.addErrors("init", Collections.singleton(msg));
	}

	private boolean isPluginsSDK(File dir) {
		if ((dir == null) || !dir.exists() || !dir.isDirectory()) {
			return false;
		}

		List<String> names = Arrays.asList(dir.list());

		return names != null &&
			names.contains("portlets") &&
			names.contains("hooks") &&
			names.contains("layouttpl") &&
			names.contains("themes") &&
			names.contains("build.properties") &&
			names.contains("build.xml") &&
			names.contains("build-common.xml") &&
			names.contains("build-common-plugin.xml");
	}

	private boolean isPluginsSDK70(File dir) {
		if ((dir == null) || !dir.exists() || !dir.isDirectory()) {
			return false;
		}

		File buildProperties = new File(dir, "build.properties");
		Properties properties = new Properties();

		InputStream in = null;

		try {
			in = new FileInputStream(buildProperties);

			properties.load(in);

			String sdkVersionValue = (String) properties.get("lp.version");

			if (sdkVersionValue.equals("7.0.0")) {
				return true;
			}
		}
		catch (Exception e) {
		}
		finally {
			if (in != null) {
				try {
					in.close();
				}
				catch (Exception e) {
				}
			}
		}

		return false;
	}

	private void moveContentsToDir(File src, File dest, final String sdkDirName)
		throws IOException {

		Path tempDir = Files.createTempDirectory("temp-plugins-sdk");

		FileFilter fileFilter = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return
					(!pathname.getName().equals(sdkDirName) ||
						!pathname.getName().startsWith("."));
			}

		};

		FileUtils.copyDirectory(src, tempDir.toFile(), fileFilter, true);

		String[] copied = tempDir.toFile().list();

		for (String name : copied) {
			IO.delete(new File(src, name));
		}

		FileUtils.moveDirectory(tempDir.toFile(), dest);
	}

	private void trace(String msg) {
		_blade.trace("%s: %s", "init", msg);
	}

	private final blade _blade;
	private final InitOptions _options;

}