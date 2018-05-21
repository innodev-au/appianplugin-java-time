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

import java.sql.Date;

import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;

/**
 * Function that parses a text representation of date and returns the corresponding date value. 
 *
 */
@TimeCategory
public class Time_parseLocalDate {

	@Function
	public Date time_parseLocalDate(@Parameter String dateText, @Parameter String pattern) throws Exception {
		try {
			return DateHelper.parseDate(dateText, pattern);
		}
		catch (InvalidPatternException e) {
			throw AppianUtil.newError("Unable parse date because pattern [" + pattern + "] is invalid. Details: " + e.getMessage(), e);
		}
		catch (Exception e) {
			throw AppianUtil.newError("Unable parse date with pattern [" + pattern + "]. Details: " + e.getMessage(), e);
		}
	}	
}
