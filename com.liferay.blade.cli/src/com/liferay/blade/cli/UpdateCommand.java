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

import aQute.lib.getopt.Options;

/**
 * @author Gregory Amerson
 */
public class UpdateCommand {

	public static final String DESCRIPTION = "Update blade to latest version";

	public UpdateCommand(blade blade, Options options) throws Exception {
		_blade = blade;
	}

	public void execute() throws Exception {
		if (Util.isWindows()) {
			_blade.out().println("blade update cannot execute successfully because of Windows file locking. Please use following command:");
			_blade.out().println("\tjpm install -f com.liferay.blade.cli");
		}
		else {
			Process process = Util.startProcess(
				_blade, "jpm install -f com.liferay.blade.cli");

			int errCode = process.waitFor();

			if (errCode == 0) {
				_blade.out().println("Update completed successfully");
			}
			else {
				_blade.error("blade exited with code: " + errCode);
			}
		}
	}

	private blade _blade;

}