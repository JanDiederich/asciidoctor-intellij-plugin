package org.asciidoc.intellij.settings.language;

import com.intellij.openapi.util.SystemInfo;
import com.intellij.util.xmlb.annotations.Attribute;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import com.intellij.util.xmlb.annotations.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.asciidoc.intellij.commandRunner.arbitrary.AsciiDocRunnerForGo;
import org.asciidoc.intellij.commandRunner.arbitrary.AsciiDocRunnerForJavaScript;
import org.asciidoc.intellij.commandRunner.arbitrary.AsciiDocRunnerForPowershell;
import org.asciidoc.intellij.commandRunner.arbitrary.AsciiDocRunnerForPython;
import org.asciidoc.intellij.commandRunner.arbitrary.AsciiDocRunnerForRuby;
import org.asciidoc.intellij.commandRunner.arbitrary.AsciiDocRunnerForTypeScript;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

@Builder
@Data
// No-arg constructor required by IntelliJ XMLB for deserialization.
@NoArgsConstructor
@AllArgsConstructor
/* @Transient on generated getters/setters prevents XMLB from using the accessor path,
 avoiding double serialization alongside the field-level @Tag annotations. */
@Getter(onMethod_ = {@Transient})
@Setter(onMethod_ = {@Transient})
public class AsciiDocScriptLanguageSettings implements Serializable {
  @Tag("languageSettingGo")
  @Property(surroundWithTag = false)
  @Nullable
  private AsciiDocScriptLanguageSetting languageSettingGo;

  @Tag("languageSettingJavaScript")
  @Property(surroundWithTag = false)
  @Nullable
  private AsciiDocScriptLanguageSetting languageSettingJavaScript;

  @Tag("languageSettingPowerShell")
  @Property(surroundWithTag = false)
  @Nullable
  private AsciiDocScriptLanguageSetting languageSettingPowerShell;

  @Tag("languageSettingPython")
  @Property(surroundWithTag = false)
  @Nullable
  private AsciiDocScriptLanguageSetting languageSettingPython;

  @Tag("languageSettingRuby")
  @Property(surroundWithTag = false)
  @Nullable
  private AsciiDocScriptLanguageSetting languageSettingRuby;

  @Tag("languageSettingTypeScript")
  @Property(surroundWithTag = false)
  @Nullable
  private AsciiDocScriptLanguageSetting languageSettingTypeScript;

  /**
   * Last selected language in the GUI. So, if a user tests again and again different interpreter parameters, the user
   * doesn't have to re-select the used language over and over.
   */
  @Attribute("selectedLanguage")
  @Nullable
  private AsciiDocLanguages selectedLanguage;

  public static AsciiDocScriptLanguageSettings build() {
    return new AsciiDocScriptLanguageSettings(
      AsciiDocScriptLanguageSetting.build(AsciiDocRunnerForGo.findGoInterpreter()),
      AsciiDocScriptLanguageSetting.build(AsciiDocRunnerForJavaScript.findJavaScriptInterpreter()),
      AsciiDocScriptLanguageSetting.build(AsciiDocRunnerForPowershell.findPowerShellInterpreter()),
      AsciiDocScriptLanguageSetting.build(AsciiDocRunnerForPython.findPythonInterpreter()),
      AsciiDocScriptLanguageSetting.build(AsciiDocRunnerForRuby.findRubyInterpreter()),
      AsciiDocScriptLanguageSetting.build(AsciiDocRunnerForTypeScript.findNodePackageExecutor(),
        SystemInfo.isWindows ? AsciiDocRunnerForTypeScript.findNpxScript() : null),
      AsciiDocLanguages.JAVA_SCRIPT
    );
  }
}
