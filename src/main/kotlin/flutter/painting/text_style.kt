package flutter

data class FontWeight(val index: Int)

val bold = FontWeight(5)

/// Whether to slant the glyphs in the font
enum class FontStyle {
  /// Use the upright glyphs
  normal,

  /// Use glyphs designed for slanting
  italic,
}

/// A horizontal line used for aligning text.
enum class TextBaseline {
  // The horizontal line used to align the bottom of glyphs for alphabetic characters.
  alphabetic,

  // The horizontal line used to align ideographic characters.
  ideographic,
}

data class TextDecoration(
    val underline: Boolean = false,
    val overline: Boolean = false,
    val lineThrough: Boolean = false
)

/// The style in which to draw a text decoration
enum class TextDecorationStyle {
  /// Draw a solid line
  solid,

  /// Draw two lines
  double,

  /// Draw a dotted line
  dotted,

  /// Draw a dashed line
  dashed,

  /// Draw a sinusoidal line
  wavy
}

enum class TextAlign {
  /// Align the text on the left edge of the container.
  left,

  /// Align the text on the right edge of the container.
  right,

  /// Align the text in the center of the container.
  center,

  /// Stretch lines of text that end with a soft line break to fill the width of
  /// the container.
  ///
  /// Lines that end with hard line breaks are aligned towards the [start] edge.
  justify,

  /// Align the text on the leading edge of the container.
  ///
  /// For left-to-right text ([TextDirection.ltr]), this is the left edge.
  ///
  /// For right-to-left text ([TextDirection.rtr]), this is the right edge.
  start,

  /// Align the text on the trailing edge of the container.
  ///
  /// For left-to-right text ([TextDirection.ltr]), this is the right edge.
  ///
  /// For right-to-left text ([TextDirection.rtr]), this is the left edge.
  end,
}

/// An immutable style in which paint text.
///
/// ## Sample code
///
/// ### Bold
///
/// Here, a single line of text in a [Text] widget is given a specific style
/// override. The style is mixed with the ambient [DefaultTextStyle] by the
/// [Text] widget.
///
/// ```dart
/// new Text(
///   'No, we need bold strokes. We need this plan.',
///   style: new TextStyle(fontWeight: FontWeight.bold),
/// )
/// ```
///
/// ### Italics
///
/// As in the previous example, the [Text] widget is given a specific style
/// override which is implicitly mixed with the ambient [DefaultTextStyle].
///
/// ```dart
/// new Text(
///   'Welcome to the present, we\'re running a real nation.',
///   style: new TextStyle(fontStyle: FontStyle.italic),
/// )
/// ```
///
/// ### Opacity
///
/// Each line here is progressively more opaque. The base color is
/// [material.Colors.black], and [Color.withOpacity] is used to create a
/// derivative color with the desired opacity. The root [TextSpan] for this
/// [RichText] widget is explicitly given the ambient [DefaultTextStyle], since
/// [RichText] does not do that automatically. The inner [TextStyle] objects are
/// implicitly mixed with the parent [TextSpan]'s [TextSpan.style].
///
/// ```dart
/// new RichText(
///   text: new TextSpan(
///     style: DefaultTextStyle.of(context).style,
///     children: <TextSpan>[
///       new TextSpan(
///         text: 'You don\'t have the votes.\n',
///         style: new TextStyle(color: Colors.black.withOpacity(0.6)),
///       ),
///       new TextSpan(
///         text: 'You don\'t have the votes!\n',
///         style: new TextStyle(color: Colors.black.withOpacity(0.8)),
///       ),
///       new TextSpan(
///         text: 'You\'re gonna need congressional approval and you don\'t have the votes!\n',
///         style: new TextStyle(color: Colors.black.withOpacity(1.0)),
///       ),
///     ],
///   ),
/// )
/// ```
///
/// ### Size
///
/// In this example, the ambient [DefaultTextStyle] is explicitly manipulated to
/// obtain a [TextStyle] that doubles the default font size.
///
/// ```dart
/// new Text(
///   'These are wise words, enterprising men quote \'em.',
///   style: DefaultTextStyle.of(context).style.apply(fontSizeFactor: 2.0),
/// )
/// ```
///
/// ### Line height
///
/// The [height] property can be used to change the line height. Here, the line
/// height is set to 100 logical pixels, so that the text is very spaced out.
///
/// ```dart
/// new Text(
///   'Don\'t act surprised, you guys, cuz I wrote \'em!',
///   style: new TextStyle(height: 100.0),
/// )
/// ```
///
/// ### Wavy red underline with black text
///
/// Styles can be combined. In this example, the misspelt word is drawn in black
/// text and underlined with a wavy red line to indicate a spelling error. (The
/// remainder is styled according to the Flutter default text styles, not the
/// ambient [DefaultTextStyle], since no explicit style is given and [RichText]
/// does not automatically use the ambient [DefaultTextStyle].)
///
/// ```dart
/// new RichText(
///   text: new TextSpan(
///     text: 'Don\'t tax the South ',
///     children: <TextSpan>[
///       new TextSpan(
///         text: 'cuz',
///         style: new TextStyle(
///           color: Colors.black,
///           decoration: TextDecoration.underline,
///           decorationColor: Colors.red,
///           decorationStyle: TextDecorationStyle.wavy,
///         ),
///       ),
///       new TextSpan(
///         text: ' we got it made in the shade',
///       ),
///     ],
///   ),
/// )
/// ```
///
/// ### Custom Fonts
///
/// Custom fonts can be declared in the `pubspec.yaml` file as shown below:
///
///```yaml
/// flutter:
///   fonts:
///     - family: Raleway
///       fonts:
///         - asset: fonts/Raleway-Regular.ttf
///         - asset: fonts/Raleway-Medium.ttf
///           weight: 500
///         - asset: assets/fonts/Raleway-SemiBold.ttf
///           weight: 600
///      - family: Schyler
///        fonts:
///          - asset: fonts/Schyler-Regular.ttf
///          - asset: fonts/Schyler-Italic.ttf
///            style: italic
///```
///
/// The `family` property determines the name of the font, which you can use in
/// the [fontFamily] argument. The `asset` property is a path to the font file,
/// relative to the `pubspec.yaml` file. The `weight` property specifies the
/// weight of the glyph outlines in the file as an integer multiple of 100
/// between 100 and 900. This corresponds to the [FontWeight] class and can be
/// used in the [fontWeight] argument. The `style` property specfies whether the
/// outlines in the file are `italic` or `normal`. These values correspond to
/// the [FontStyle] class and can be used in the [fontStyle] argument.
///
/// To select a custom font, create [TextStyle] using the [fontFamily]
/// argument as shown in the example below:
///
/// ```dart
/// const TextStyle(fontFamily: 'Raleway')
/// ```
///
/// To use a font family defined in a package, the [package] argument must be
/// provided. For instance, suppose the font declaration above is in the
/// `pubspec.yaml` of a package named `my_package` which the app depends on.
/// Then creating the TextStyle is done as follows:
///
/// ```dart
/// const TextStyle(fontFamily: 'Raleway', package: 'my_package')
/// ```
///
/// This is also how the package itself should create the style.
///
/// A package can also provide font files in its `lib/` folder which will not
/// automatically be included in the app. Instead the app can use these
/// selectively when declaring a font. Suppose a package named `my_package` has:
///
/// ```
/// lib/fonts/Raleway-Medium.ttf
/// ```
///
/// Then the app can declare a font like in the example below:
///
///```yaml
/// flutter:
///   fonts:
///     - family: Raleway
///       fonts:
///         - asset: assets/fonts/Raleway-Regular.ttf
///         - asset: packages/my_package/fonts/Raleway-Medium.ttf
///           weight: 500
///```
///
/// The `lib/` is implied, so it should not be included in the asset path.
///
/// See also:
///
///  * [Text], the widget for showing text in a single style.
///  * [DefaultTextStyle], the widget that specifies the default text styles for
///    [Text] widgets, configured using a [TextStyle].
///  * [RichText], the widget for showing a paragraph of mix-style text.
///  * [TextSpan], the class that wraps a [TextStyle] for the purposes of
///    passing it to a [RichText].
data class TextStyle(
    /// Whether null values are replaced with their value in an ancestor text
    /// style (e.g., in a [TextSpan] tree).
    ///
    /// If this is false, properties that don't have explicit values will revert
    /// to the defaults: white in color, a font size of 10 pixels, in a sans-serif
    /// font face.
    val inherit: Boolean = true,

    /// The name of the font to use when painting the text (e.g., Roboto).
    val fontFamily: String? = null,

    /// The color to use when painting the text.
    val color: Color? = null,

    /// The size of glyphs (in logical pixels) to use when painting the text.
    ///
    /// During painting, the [fontSize] is multiplied by the current
    /// `textScaleFactor` to let users make it easier to read text by increasing
    /// its size.
    ///
    /// [getParagraphStyle] will default to 14 logical pixels if the font size
    /// isn't specified here.
    val fontSize: Double? = null,

    /// The typeface thickness to use when painting the text (e.g., bold).
    val fontWeight: FontWeight? = null,

    /// The typeface variant to use when drawing the letters (e.g., italics).
    val fontStyle: FontStyle? = null,

    /// The amount of space (in logical pixels) to add between each letter.
    /// A negative value can be used to bring the letters closer.
    val letterSpacing: Double? = null,

    /// The amount of space (in logical pixels) to add at each sequence of
    /// white-space (i.e. between each word). A negative value can be used to
    /// bring the words closer.
    val wordSpacing: Double? = null,

    /// The common baseline that should be aligned between this text span and its
    /// parent text span, or, for the root text spans, with the line box.
    val textBaseline: TextBaseline? = null,

    /// The height of this text span, as a multiple of the font size.
    ///
    /// If applied to the root [TextSpan], this value sets the line height, which
    /// is the minimum distance between subsequent text baselines, as multiple of
    /// the font size.
    val lineHeight: Double? = null,

    /// The decorations to paint near the text (e.g., an underline).
    val decoration: TextDecoration? = null,

    /// The color in which to paint the text decorations.
    val decorationColor: Color? = null,

    /// The style in which to paint the text decorations (e.g., dashed).
    val decorationStyle: TextDecorationStyle? = null
)