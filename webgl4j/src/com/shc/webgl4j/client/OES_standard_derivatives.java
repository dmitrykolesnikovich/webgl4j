/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Sri Harsha Chilakapati
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.shc.webgl4j.client;

/**
 * @author Sri Harsha Chilakapati
 */
public final class OES_standard_derivatives
{
    public static final int GL_FRAGMENT_SHADER_DERIVATIVE_HINT = 0x8B8B;

    /* Prevent instantiation */
    private OES_standard_derivatives()
    {
    }

    public static boolean isSupported()
    {
        if (!WebGL10.isSupported())
            return false;

        if (!WebGL10.isContextCompatible())
            throw new IllegalStateException("You must have a WebGL context >= 1.0 to check if extension is supported.");

        for (String supportedExtension : WebGL10.glGetSupportedExtensions())
        {
            switch (supportedExtension)
            {
                case "OES_standard_derivatives":
                case "O_OES_standard_derivatives":
                case "IE_OES_standard_derivatives":
                case "MOZ_OES_standard_derivatives":
                case "WEBKIT_OES_standard_derivatives":
                    return true;
            }
        }

        return false;
    }

    public static void enableExtension()
    {
        if (!WebGL10.isContextCompatible())
            throw new IllegalStateException("You must have a WebGL context >= 1.0 to enable this extension.");

        if (!isSupported())
            throw new RuntimeException("This browser does not support the OES_standard_derivatives extension.");

        if (!isExtensionEnabled())
            nEnableExtension();
    }

    public static native boolean isExtensionEnabled() /*-{
        return typeof ($wnd.gl.osd_ext) !== 'undefined';
    }-*/;

    private static native void nEnableExtension() /*-{
        $wnd.gl.osd_ext = $wnd.gl.getExtension('OES_standard_derivatives') ||
            $wnd.gl.getExtension('O_OES_standard_derivatives') ||
            $wnd.gl.getExtension('IE_OES_standard_derivatives') ||
            $wnd.gl.getExtension('MOZ_OES_standard_derivatives') ||
            $wnd.gl.getExtension('WEBKIT_OES_standard_derivatives');
    }-*/;
}
