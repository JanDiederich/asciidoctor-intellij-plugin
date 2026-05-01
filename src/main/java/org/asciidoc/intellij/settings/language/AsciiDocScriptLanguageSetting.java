package org.asciidoc.intellij.settings.language;

import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Transient;
import com.intellij.util.xmlb.annotations.XCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.List;

/**
 * Settings on arbitrary language.
 */
@Data
// No-arg constructor required by IntelliJ XMLB for deserialization.
@NoArgsConstructor
@AllArgsConstructor
/* @Transient on generated getters/setters prevents XMLB from using the accessor path,
 avoiding double serialization alongside the field-level @XCollection annotations. */
@Getter(onMethod_ = {@Transient})
@Setter(onMethod_ = {@Transient})
public class AsciiDocScriptLanguageSetting implements Serializable {
  @Attribute("interpreterPath")
  @NotNull
  private String interpreterPath = "";

  @Attribute("utilPath")
  @Nullable
  private String utilPath = "";

  @XCollection(propertyElementName = "parameters", elementName = "parameter")
  @Nullable
  private List<String> parameters;

  @Nullable
  public static AsciiDocScriptLanguageSetting build(@Nullable String interpreter) {
    if (interpreter != null) {
      return new AsciiDocScriptLanguageSetting(interpreter, null, null);
    } else {
      return null;
    }
  }

  @Nullable
  public static AsciiDocScriptLanguageSetting build(@Nullable String interpreter, @Nullable String utilPath) {
    if (interpreter != null) {
      return new AsciiDocScriptLanguageSetting(interpreter, utilPath, null);
    } else {
      return null;
    }
  }

  /**
   * Expand parameters into single line.
   *
   * @return Expanded parameters.
   */
  @Nullable
  public String expandParameters() {
    if (parameters == null) {
      return null;
    }
    return String.join(" ", parameters);
  }

  public boolean isValid() {
    //noinspection ConstantValue
    return interpreterPath != null && !interpreterPath.isEmpty();
  }
}
