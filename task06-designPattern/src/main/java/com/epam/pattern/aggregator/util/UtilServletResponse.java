package com.epam.pattern.aggregator.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Aliaksandr_Shynkevich on 2/15/15
 */
public final class UtilServletResponse {
    private UtilServletResponse() {}

    public static void printJson(String body, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "nocache");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.print(body);
    }
}
