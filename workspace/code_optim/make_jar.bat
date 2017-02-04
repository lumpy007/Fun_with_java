
javac -classpath .classpath src\code_optim\*.java

(
echo Manifest-Version: 1.0 
echo Class-Path: .
echo Main-Class: code_optim.control 
) > MANIFEST.MF

cd src

jar cmf ..\MANIFEST.MF ..\code_explore.jar code_optim\*.class -C code_optim\recources .

del ..\MANIFEST.MF
cd ..