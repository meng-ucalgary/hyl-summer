package ca.hackyourlearning;

/*
 * Anatomy of ANSI color codes
 *
 * \033[0m          Reset
 * \033[X;Ym        Before string to be colored
 *
 * \033[            the escape sequence initiation of colors, I guess?
 *
 * X                flag (referred as TextMode in the program). It can have following values
 *                  +-------+-------------------------------------------+
 *                  | Code  | Function                                  |
 *                  +-------+-------------------------------------------+
 *                  | 0     | Reset or Normal                           |
 *                  | 1     | Bold or High Intensity                    |
 *                  | 2     | Faint or Low Intensity                    |
 *                  | 3     | Italics                                   |
 *                  | 4     | Underline                                 |
 *                  | 5     | Slow Blink                                |
 *                  | 6     | Rapid Blink                               |
 *                  | 7     | Reversed or Foreground/Background swapped |
 *                  | 8     | Hide                                      |
 *                  | 9     | Strikethrough                             |
 *                  +-------+-------------------------------------------+
 *
 *
 * Y                the actual code for the color
 *                  +-------------------+-------------------+-------------------+
 *                  | Color             | Foreground Code   | Background Code   |
 *                  +-------------------+-------------------+-------------------+
 *                  | Black             | 30                | 40                |
 *                  | Red               | 31                | 41                |
 *                  | Green             | 32                | 42                |
 *                  | Yellow            | 33                | 43                |
 *                  | Blue              | 34                | 44                |
 *                  | Magenta           | 35                | 45                |
 *                  | Cyan              | 36                | 46                |
 *                  | White             | 37                | 47                |
 *                  +-------------------+-------------------+-------------------+
 *                  | Bright Black/Gray | 90                | 100               |
 *                  | Bright Red        | 91                | 101               |
 *                  | Bright Green      | 92                | 102               |
 *                  | Bright Yellow     | 93                | 103               |
 *                  | Bright Blue       | 94                | 104               |
 *                  | Bright Magenta    | 95                | 105               |
 *                  | Bright Cyan       | 96                | 106               |
 *                  | Bright White      | 97                | 107               |
 *                  +-------------------+-------------------+-------------------+
 */

/**
 * Provides ANSI colored output
 *
 * @author Bhavyai Gupta
 * @version 1.0.1
 * @since May 7, 2021
 */
public class ColorText {
	private static String partOne;
	private static String partTwo;
	private static String partThree;
	private final static String RESET = "\033[0m";

	/**
	 * Constructor is declared private to prevent instantiation of the class
	 */
	private ColorText() {
	}

	/**
	 * Returns the ANSI code of the foreground, regular {@link Color}
	 *
	 * @param color    of type {@link Color}
	 * @param textmode of type {@link TextMode}
	 * @return a string representing of the argument
	 */
	private static String getANSI(Color color) {
		return getANSI(color, TextMode.NORMAL, false);
	}

	/**
	 * Returns the ANSI code of the foreground {@link Color} with flag
	 * {@link TextMode}
	 *
	 * @param color    of type {@link Color}
	 * @param textmode of type {@link TextMode}
	 * @return a string representing of the argument
	 */
	private static String getANSI(Color color, TextMode textmode) {
		return getANSI(color, textmode, false);
	}

	/**
	 * Returns the ANSI code of the foreground or background {@link Color} with flag
	 * {@link TextMode}
	 *
	 * @param color      of type {@link Color}
	 * @param textmode   of type {@link TextMode}
	 * @param background {@code true} for background, {@code false} for foreground
	 * @return a string representing of the argument
	 */
	private static String getANSI(Color color, TextMode textmode, boolean background) {
		partOne = "\033[";
		partTwo = String.valueOf(textmode.getCode()) + ";";

		if (background == false) {
			partThree = String.valueOf(color.getCode()) + "m";
		}

		else {
			partThree = String.valueOf(color.getCode() + 10) + "m";
		}

		return (partOne + partTwo + partThree);
	}

	/**
	 * Returns the colored text of string with the specified foreground
	 * {@link Color}
	 *
	 * @param str   the string to be colored
	 * @param color of type {@link Color}
	 * @return a string representing of the argument
	 */
	public static String text(String str, Color color) {
		String temp = String.format(getANSI(color) + str + RESET);
		return temp;
	}

	/**
	 * Returns the colored text of string with the specified foreground
	 * {@link Color} and flag {@link TextMode}
	 *
	 * @param str      the string to be colored
	 * @param color    of type {@link Color}
	 * @param textmode of type {@link TextMode}
	 * @return a string representing of the argument
	 */
	public static String text(String str, Color color, TextMode textmode) {
		String temp = String.format(getANSI(color, textmode) + str + RESET);
		// System.out.print(temp);
		return temp;
	}

	/**
	 * Returns the colored text of string with specified {@link Color} with flag
	 * {@link TextMode}, with the choice of foreground or background
	 *
	 * @param str        the string to be colored
	 * @param color      of type {@link Color}
	 * @param textmode   of type {@link TextMode}
	 * @param background {@code true} for background, {@code false} for foreground
	 * @return a string representing of the argument
	 */
	public static String text(String str, Color color, TextMode textmode, boolean background) {
		String temp = String.format(getANSI(color, textmode, background) + str + RESET);
		// System.out.print(temp);
		return temp;
	}
}

/**
 * Enum Color to hold the partThree of the ANSI sequence
 */
enum Color {
	BLACK(30), RED(31), GREEN(32), YELLOW(33), BLUE(34), MAGENTA(35), CYAN(36), WHITE(37), GRAY(90), BRIGHT_RED(91),
	BRIGHT_GREEN(92), BRIGHT_YELLOW(93), BRIGHT_BLUE(94), BRIGHT_MAGENTA(95), BRIGHT_CYAN(96), BRIGHT_WHITE(97);

	private int c;

	Color(int x) {
		this.c = x;
	}

	int getCode() {
		return this.c;
	}
}

/**
 * Enum TextMode to hold the partTwo of the ANSI sequence
 */
enum TextMode {
	NORMAL(0), BOLD(1), FAINT(2), ITALICS(3), UNDERLINE(4), SLOWBLINK(5), RAPIDBLINK(6), REVERSE(7), HIDE(8),
	STRIKETHROUGH(9);

	private int c;

	TextMode(int x) {
		this.c = x;
	}

	int getCode() {
		return this.c;
	}
}
