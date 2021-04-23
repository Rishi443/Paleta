package org.opens.utils.contrastchecker;

import java.awt.Color;

 
public final class ContrastChecker {
    /* */

    private static final double RED_factor = 0.2126;
    /* */
    private static final double GREEN_factor = 0.7152;
    /* */
    private static final double BLUE_factor = 0.0722;
    /* */
    private static final double CONTRAST_factor = 0.05;
    /* */
    private static final double RGB_MAX_VALUE = 255;
    /* */
    private static final double RSGB_factor = 0.03928;
    /* */
    private static final double LUMINANCE_INF = 12.92;
    /* */
    private static final double LUMINANCE_SUP_CONST = 0.055;
    /* */
    private static final double LUMINANCE_SUP_CONST2 = 1.055;
    /* */
    private static final double LUMINANCE_EXP = 2.4;
    /* */
    private static final int ROUND_VALUE = 100000;

   
    private ContrastChecker() {
    }

    public static double distanceColor(final Color fgColor, final Color bgColor) {
        int redFg = fgColor.getRed();
        int redBg = bgColor.getRed();
        int greenBg = bgColor.getGreen();
        int greenFg = fgColor.getGreen();
        int blueFg = fgColor.getBlue();
        int blueBg = bgColor.getBlue();
        return (Math.sqrt(Math.pow(redFg - redBg, 2) + Math.pow(greenFg - greenBg, 2) + Math.pow(blueFg - blueBg, 2)));
    }

   
    public static boolean isContrastValid(final Color fgColor, final Color bgColor, Float coefficientLevel) {
        return getConstrastRatio(fgColor, bgColor) > coefficientLevel;
    }

    
    public static double getConstrastRatio(final Color fgColor, final Color bgColor) {
        double fgLuminosity = getLuminosity(fgColor);
        double bgLuminosity = getLuminosity(bgColor);
        if (fgLuminosity > bgLuminosity) {
            return computeContrast(fgLuminosity, bgLuminosity);
        } else {
            return computeContrast(bgLuminosity, fgLuminosity);
        }
    }

  

    
    private static double computeContrast(double lighter, double darker) {
        return (Double.valueOf(((lighter + CONTRAST_factor) / (darker + CONTRAST_factor))));
    }

    
    public static double getLuminosity(Color color) {
        double luminosity =
                getComposantValue(color.getRed()) * RED_factor
                + getComposantValue(color.getGreen()) * GREEN_factor
                + getComposantValue(color.getBlue()) * BLUE_factor;

        return luminosity;
    }

  
    private static double getComposantValue(double composant) {
        double rsgb = composant / RGB_MAX_VALUE;
        if (rsgb <= RSGB_factor) {
            return rsgb / LUMINANCE_INF;
        } else {
            return Math.pow(((rsgb + LUMINANCE_SUP_CONST) / LUMINANCE_SUP_CONST2), LUMINANCE_EXP);
        }
    }

    public static double getGrayScale(Color color) {
        double grayscale = (color.getRed()) + (color.getBlue()) + (color.getGreen());
        return grayscale/3;
    }
}