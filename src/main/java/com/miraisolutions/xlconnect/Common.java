/*
 *
    XLConnect
    Copyright (C) 2010 Mirai Solutions GmbH

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.miraisolutions.xlconnect;

import java.util.ArrayList;

public class Common implements SupportsWarnings {

    /* Warnings -
     * This is used to support the warnings mechanism on the R side
     */
    private final ArrayList<String> warnings = new ArrayList<String>();

    public void addWarning(String warning) {
        warnings.add(warning);
    }

    public String[] retrieveWarnings() {
        String[] warn = warnings.toArray(new String[warnings.size()]);
        warnings.clear();
        return warn;
    }
}
