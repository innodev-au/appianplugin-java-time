/**
 * Copyright 2018 Innodev
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package innodev.appianplugin.functions.time;

import org.threeten.bp.format.DateTimeParseException;

import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;

/**
 * Function that determines whether a local date is valid. 
 *
 */
@TimeCategory
public class Time_isValidLocalDate {

	@Function
	public boolean time_isValidLocalDate(@Parameter String dateText, @Parameter String pattern) throws Exception {
		boolean valid;
		try {
			DateHelper.parseDate(dateText, pattern);
			valid = true;
		} catch (DateTimeParseException e) {
			valid =false;
		} catch (InvalidPatternException e) {
			throw AppianUtil.newError(
					"Unable parse date because pattern [" + pattern + "] is invalid. Details: " + e.getMessage(), e);
		} catch (Exception e) {
			throw AppianUtil.newError("Unable parse date with pattern [" + pattern + "]. Details: " + e.getMessage(),
					e);
		}

		return valid;

	}
}
