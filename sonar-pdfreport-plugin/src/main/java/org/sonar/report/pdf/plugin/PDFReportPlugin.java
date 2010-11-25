/*
 * Sonar PDF Report (Sonar Plugin)
 * Copyright (C) 2010 klicap - ingenieria del puzle
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */

package org.sonar.report.pdf.plugin;

import org.sonar.api.Extension;
import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.report.pdf.batch.PDFMavenPluginHandler;
import org.sonar.report.pdf.batch.PDFPostJob;
import org.sonar.report.pdf.web.ReportWebService;

import java.util.ArrayList;
import java.util.List;

@Properties({
  @Property(
    key=PDFPostJob.SKIP_PDF_KEY,
    name="Skip",
    description = "Skip generation of PDF report.",
    defaultValue = "" + PDFPostJob.SKIP_PDF_DEFAULT_VALUE,
    global = true,
    project = true,
    module = false
  ),
  @Property(
    key=PDFPostJob.REPORT_TYPE,
    name="Type",
    description = "Report type (executive or workbook).",
    defaultValue = PDFPostJob.REPORT_TYPE_DEFAULT_VALUE,
    global = true,
    project = true,
    module = false
  ),
  @Property(
    key=PDFPostJob.USERNAME,
    name="Username",
    description = "Username for WS API access.",
    defaultValue = PDFPostJob.USERNAME_DEFAULT_VALUE,
    global = true,
    project = true,
    module = false
  ),
  @Property(
    key=PDFPostJob.PASSWORD,
    name="Password",
    description = "Password for WS API access.",
    defaultValue = PDFPostJob.PASSWORD_DEFAULT_VALUE,
    global = true,
    project = true,
    module = false
  )
})
public class PDFReportPlugin implements Plugin {

  public static final String PLUGIN_KEY = "pdf-report";

  public String getKey() {
    return PLUGIN_KEY;
  }

  public String getName() {
    return "PDF Report";
  }

  public String getDescription() {
    return "Generate a PDF report that contains the most relevant information from project analysis.";
  }

  public List<Class< ? extends Extension>> getExtensions() {
    List<Class< ? extends Extension>> extensions = new ArrayList<Class< ? extends Extension>>();
    extensions.add(PDFMavenPluginHandler.class);
    extensions.add(PDFPostJob.class);
    extensions.add(ReportDataMetric.class);
    extensions.add(ReportWebService.class);
    extensions.add(PdfReportWidget.class);
    return extensions;
  }
}
