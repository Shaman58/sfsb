openssl req -x509 -out cert.pem -keyout key.pem \
  -newkey rsa:2048 -nodes -sha256 \
  -subj '/CN=ra58.ru' -extensions EXT -config <( \
   printf "[dn]\nCN=ra.58\n[req]\ndistinguished_name = dn\n[EXT]\nsubjectAltName=DNS:ra58.ru\nkeyUsage=digitalSignature\nextendedKeyUsage=serverAuth")