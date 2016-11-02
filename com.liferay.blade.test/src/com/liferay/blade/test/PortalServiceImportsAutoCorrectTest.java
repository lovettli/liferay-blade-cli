
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

package com.liferay.blade.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import aQute.lib.io.IO;

import com.liferay.blade.api.AutoMigrator;
import com.liferay.blade.api.FileMigrator;
import com.liferay.blade.api.Problem;

import java.io.File;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

public class PortalServiceImportsAutoCorrectTest {

	@Test
	public void autoCorrectProblems() throws Exception {
		File tmpfolder = Files.createTempDirectory("autocorrect").toFile();
		File testfile = new File(tmpfolder, "PortalServiceImports.java");
		tmpfolder.deleteOnExit();

		File originalTestfile = new File("javatests/PortalServiceImports.java");
		IO.copy(originalTestfile, testfile);

		List<Problem> problems = null;
		FileMigrator migrator = null;

		Collection<ServiceReference<FileMigrator>> mrefs = context.getServiceReferences(FileMigrator.class, null);
		for (ServiceReference<FileMigrator> mref : mrefs) {
			migrator = context.getService(mref);

			if (migrator.getClass().getName().contains("PortalServiceImports")) {
				problems = migrator.analyze(testfile);
				break;
			}
		}

		assertEquals(2, problems.size());

		((AutoMigrator)migrator).correctProblems( testfile, problems );

		File dest = new File(tmpfolder, "Updated.java");

		assertTrue(testfile.renameTo(dest));

		problems = migrator.analyze(dest);

		assertEquals(0, problems.size());
	}

	private final BundleContext context = FrameworkUtil.getBundle(
		this.getClass()).getBundleContext();
}
