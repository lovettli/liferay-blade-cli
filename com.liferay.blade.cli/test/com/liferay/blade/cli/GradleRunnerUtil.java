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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import aQute.lib.io.IO;

import java.io.File;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.BuildTask;
import org.gradle.testkit.runner.GradleRunner;
import org.gradle.testkit.runner.TaskOutcome;

/**
 * @author Lawrence Lee
 */
public class GradleRunnerUtil {

	public static BuildTask executeGradleRunner (String projectPath, String... taskPath) {
		File projectDir = new File(projectPath);

		BuildResult buildResult = GradleRunner.create()
									.withProjectDir(projectDir)
									.withArguments(taskPath)
									.build();

		BuildTask buildtask = null;

		for (BuildTask task : buildResult.getTasks()) {
			if (task.getPath().endsWith(taskPath[taskPath.length - 1])) {
				buildtask = task;
				break;
			}
		}

		return buildtask;
	}

	public static void verifyGradleRunnerOutput (BuildTask buildtask) {
		assertNotNull(buildtask);

		assertEquals(buildtask.getOutcome(), TaskOutcome.SUCCESS);
	}

	public static void verifyBuildOutput (String projectPath, String fileName) {
		File file = IO.getFile(projectPath + "/build/libs/" + fileName);

		assertTrue(file.exists());
	}

}
