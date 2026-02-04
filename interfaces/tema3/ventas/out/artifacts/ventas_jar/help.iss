; Script modificado para ejecutar el archivo JAR correctamente
#define MyAppName "Ventas App"
#define MyAppVersion "1.5"
#define MyAppPublisher "My Company, Inc."
#define MyAppURL "https://www.example.com/"
; Ahora el ejecutable principal es tu archivo JAR
#define MyAppJarName "ventas.jar"
#define MyAppAssocName "ventas"
#define MyAppAssocExt ".myp"
#define MyAppAssocKey StringChange(MyAppAssocName, " ", "") + MyAppAssocExt

[Setup]
AppId={{A4F38349-260E-4592-87AB-94C0AE672633}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
DefaultDirName={autopf}\{#MyAppName}
; Usamos javaw para que no se abra una ventana de consola negra
UninstallDisplayIcon={app}\{#MyAppJarName}
ArchitecturesAllowed=x64compatible
ArchitecturesInstallIn64BitMode=x64compatible
ChangesAssociations=yes
DisableProgramGroupPage=yes
PrivilegesRequired=lowest
OutputDir=C:\Users\dam2\Desktop\jaspersoftstudio
OutputBaseFilename=mysetup_ventas
SolidCompression=yes
WizardStyle=modern

[Languages]
Name: "spanish"; MessagesFile: "compiler:Languages\Spanish.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
; IMPORTANTE: He corregido la fuente para que use tu JAR real
Source: "C:\Users\dam2\Desktop\DAM2.1\interfaces\tema3\ventas\out\artifacts\ventas_jar\ventas.jar"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
; El icono ahora llama a javaw.exe para ejecutar el JAR
Name: "{autoprograms}\{#MyAppName}"; Filename: "javaw.exe"; Parameters: "-jar ""{app}\{#MyAppJarName}"""
Name: "{autodesktop}\{#MyAppName}"; Filename: "javaw.exe"; Parameters: "-jar ""{app}\{#MyAppJarName}"""; Tasks: desktopicon

[Run]
; Ejecutar al finalizar la instalación
Filename: "javaw.exe"; Parameters: "-jar ""{app}\{#MyAppJarName}"""; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent
