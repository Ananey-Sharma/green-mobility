# Security Notes

- Do not commit real database passwords, API keys, tunnel credentials, or other secrets.
- Use environment variables for local database credentials.
- The sample credentials in `db/schema.sql` are demonstration data only.
- For a production deployment, passwords should be stored as salted password hashes rather than plaintext values.
- HTTPS and appropriate authentication/session hardening should be added before production use.
