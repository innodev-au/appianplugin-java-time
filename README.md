An Appian plugin containing date and time utility functions, using concepts and behaviour from Java Time (JSR-310).

Currently, it provides functions to parse and validate text representation of local dates. Future versions may add additional functions.

## Functions

The following functions are currently provided:

- **time_parseLocalDate**: Parses a text representation of a local date. If the representation is invalid or constitutes an invalid date, an exception is thrown.
- **time_isValidLocalDate**: Returns a boolean indicating whether the provided text is a valid representation of a date given the date pattern.

A local date is not associated to a particular timezone. It can be used to represent dates where a given timezone is assumed or to represent stand-alone date values, such as a date of birth. In Appian, a _local date_ is known as a _Date_.

The date pattern follows Java's format. You may use a value such as 'yyyy-M-d'. Use 'y' for year digits, 'M' for month digits (note that it's in uppercase) and 'd' for day-of-month digits. The pattern follows what's defined in https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html#patterns, with the restriction that only date-related characters are allowed. For instance, time pattern characters such as 'H' are not allowed.

Functions are prefixed with _time__ as a rudimentary way to avoid names chashing with functions from other plugins.

## Version

The implementation is stable, hence the version number is 1.0.0.

This plugin has been tested in Appian 18.1 and 17.4 but will most likely work in newer as well as older Appian versions.

Java Time (JSR-310) was introduced in Java 8. However, this implementation runs in Appian instances with older Java versions.

## Similar Plugins

Another plugin, The _Date and Time Utilities_ plugin, also provides date parsing and validation functions. However, the functionality in this project differs in that:

- This project uses Java Time conepts and behaviour
- In this project, an arbitrary date format can be specified; the other plugin uses a fixed set of patterns.
- The return value of _time_parseLocalDate_ is a local date (i.e., an Appian _Date_); the other plugin returns an instant (i.e. an Appian _Date and Time_).
- In this project, if the parse fails, an exceptions is thrown. This is by design. If you're unsure whether the date is valid, call _time_isValidLocalDate_ before.
