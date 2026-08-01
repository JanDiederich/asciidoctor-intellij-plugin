if (window.__IntelliJTools === undefined) {
  window.__IntelliJTools = {}
}

window.__IntelliJTools.getSourceLanguageIconName = function (lang) {
  let normalized = (lang || '').trim().toLowerCase()
  normalized = normalized.replace(/^language[:\s-]*/g, '').split(/[,\s/()[\]{}<>]+/)[0]
  switch (normalized) {
    case 'go':
    case 'golang':
      return 'Go_Logo_Blue.svg'
    case 'powershell':
    case 'pwsh':
    case 'ps1':
      return 'PowerShell_5.0_icon.png'
    case 'python':
    case 'py':
      return 'python-logo-only.svg'
    case 'ruby':
    case 'rb':
      return 'Ruby_logo.svg'
    case 'typescript':
    case 'ts':
      return 'Typescript_logo_2020.svg'
    default:
      return null
  }
}

window.__IntelliJTools.injectSourceLanguageIconStyle = function () {
  if (document.getElementById('intellij-language-icon-style')) {
    return
  }
  let style = document.createElement('style')
  style.id = 'intellij-language-icon-style'
  // language=CSS
  style.textContent =
    `.listingblock code[data-lang].intellij-language-iconized::before {
      background-image: var(--intellij-language-icon);
      background-repeat: no-repeat;
      background-size: 1.9em 1.9em;
      background-position: left center;
      padding-left: 2.35em;
      min-height: 1.9em;
      line-height: 1.9em;
      opacity: 1;
    }`;
  document.head.appendChild(style)
}

window.__IntelliJTools.addSourceLanguageIcons = function () {
  if (!window.JavaPanelBridge || !window.JavaPanelBridge.languageIconBaseUrl) {
    return
  }

  window.__IntelliJTools.injectSourceLanguageIconStyle()

  let codeElements = document.querySelectorAll('.listingblock code[data-lang]')
  for (let i = 0; i < codeElements.length; i++) {
    let codeEl = codeElements[i]
    let lang = codeEl.getAttribute('data-lang') || ''
    let iconName = window.__IntelliJTools.getSourceLanguageIconName(lang)
    if (!iconName) {
      codeEl.classList.remove('intellij-language-iconized')
      codeEl.style.removeProperty('--intellij-language-icon')
      continue
    }

    codeEl.classList.add('intellij-language-iconized')
    codeEl.style.setProperty('--intellij-language-icon',
      'url("' + window.JavaPanelBridge.languageIconBaseUrl + encodeURIComponent(iconName) + '")')
  }
}
