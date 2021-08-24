keytool -genkeypair -alias dummy -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -validity 3652 -keypass passphrase -keystore keystore -storepass passphrase -storetype PKCS12
keytool -genkeypair -alias dummyecdsa -keyalg EC -keysize 256 -sigalg SHA256withECDSA -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -validity 3652 -keypass passphrase -keystore keystore -storepass passphrase -storetype PKCS12
keytool -genkeypair -alias dummydsa -keyalg DSA -keysize 1024 -sigalg SHA256withDSA -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -validity 3652 -keypass passphrase -keystore keystore -storepass passphrase -storetype PKCS12
copy keystore truststore
keytool -genkeypair -alias dummyecrsa -keyalg EC -keysize 256 -keypass passphrase -storepass passphrase -keystore keystore -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -storetype PKCS12
keytool -certreq -alias dummyecrsa -storepass passphrase -keystore keystore -file ecrsa.csr -storetype PKCS12
keytool -gencert -alias dummy -storepass passphrase -keystore keystore -validity 3652 -infile ecrsa.csr -outfile ecrsa.cer -storetype PKCS12
keytool -importcert -alias dummyecrsa -storepass passphrase -keystore keystore -file ecrsa.cer -storetype PKCS12

keytool -genkeypair -alias dummy -keyalg RSA -keysize 2048 -sigalg SHA256withRSA -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -validity 3652 -keypass passphrase -keystore keystore1 -storepass passphrase -storetype PKCS12
keytool -genkeypair -alias dummyecdsa -keyalg EC -keysize 256 -sigalg SHA256withECDSA -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -validity 3652 -keypass passphrase -keystore keystore1 -storepass passphrase -storetype PKCS12
keytool -genkeypair -alias dummydsa -keyalg DSA -keysize 1024 -sigalg SHA256withDSA -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -validity 3652 -keypass passphrase -keystore keystore1 -storepass passphrase -storetype PKCS12
copy keystore1 truststore1
keytool -genkeypair -alias dummyecrsa -keyalg EC -keysize 256 -keypass passphrase -storepass passphrase -keystore keystore1 -dname "CN=dummy.example.com, OU=Dummy, O=Dummy, L=Cupertino, ST=CA, C=US" -storetype PKCS12
keytool -certreq -alias dummyecrsa -storepass passphrase -keystore keystore1 -file ecrsa.csr -storetype PKCS12
keytool -gencert -alias dummy -storepass passphrase -keystore keystore1 -validity 3652 -infile ecrsa.csr -outfile ecrsa.cer -storetype PKCS12
keytool -importcert -alias dummyecrsa -storepass passphrase -keystore keystore1 -file ecrsa.cer -storetype PKCS12