@echo off

rem You might have to change some paths here

echo Setting PATH variable...
PATH=%PATH%;"%JAVA_HOME%\jdk1.6.0_26\bin\"
PATH=%PATH%;"C:\Program Files\Android\android-sdk\platforms\android-7\"
PATH=%PATH%;"C:\Program Files\Android\android-sdk\platforms\android-7\tools"
PATH=%PATH%;"C:\Program Files\Android\android-sdk\tools"

echo Setting paths to libraries...
set androidjar="C:\Program Files\Android\android-sdk\platforms\android-7\android.jar"
set androidjars=%androidjar%;"C:\Program Files\Android\android-sdk\extras\android\compatibility\v4\android-support-v4.jar"

rem You don't have to change anything below...
rem except if you want to use this script to
rem create an unsigned APK

rem echo Creating resources...
rem aapt package -f -M %CD%\AndroidManifest.xml -F %CD%\resources.zip -I %androidjar% -S %CD%\res -m -J %CD%\gen

echo Compiling files...
dir src\*.java /B/S > files.txt
rem dir gen\*.java /B/S >> files.txt
javac -d bin -classpath %androidjars% @files.txt
del files.txt

echo Converting bytecode...
call dx.bat --dex --keep-classes --output=%CD%\fragment.zip %CD%\bin

rem echo Building APK...
rem call apkbuilder %CD%\fragment.apk -u -z %CD%\resources.zip -f %CD%\fragment.zip 2>nul

echo Cleaning up...
rem del fragment.zip
rem del resources.zip

rem aapt list -v fragment.apk

echo Done.

pause