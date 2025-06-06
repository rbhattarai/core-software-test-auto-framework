#!/bin/bash

# --- Parse Args ---
for i in "$@"; do
  case $i in
    -browser=*|--browser=*)
      BROWSER="${i#*=}"
      shift
      ;;
    -role=*|--role=*)
      ROLE="${i#*=}"
      shift
      ;;
    -password=*|--password=*)
      PASSWORD="${i#*=}"
      shift
      ;;
    *)
      echo "Unknown option $i"
      exit 1
      ;;
  esac
done

# --- Defaults ---
BROWSER=${BROWSER:-chrome}
ROLE=${ROLE:-admin}
PASSWORD=${PASSWORD:-changeit}

CERT_FILE="certs/rohan_bhattarai_proj_${ROLE}.p12"
OS=$(uname | tr '[:upper:]' '[:lower:]')

echo "Installing certificate for role=$ROLE on OS=$OS..."

# --- Install certificate ---
if [[ "$OS" == "linux" || "$OS" == "darwin" ]]; then
  if ! command -v pk12util >/dev/null; then
    echo "Error: pk12util not found. Install with 'sudo apt install libnss3-tools'"
    exit 1
  fi

  NSSDB="$HOME/.pki/nssdb"
  mkdir -p "$NSSDB"
  certutil -N -d sql:"$NSSDB" --empty-password
  pk12util -i "$CERT_FILE" -d sql:"$NSSDB" -W "$PASSWORD"

elif [[ "$OS" == "mingw"* || "$OS" == "msys"* || "$OS" == "cygwin"* ]]; then
  # Windows Git Bash or WSL with Windows certutil
  powershell.exe -Command "certutil -f -p '$PASSWORD' -importpfx '$(pwd)\\$CERT_FILE'"
else
  echo "Unsupported OS: $OS"
  exit 1
fi

echo "Certificate installed. Launching test..."

# --- Run Maven Test ---
mvn exec:java \
  -Dexec.mainClass="com.example.CertificateRoleBasedTest" \
  -Dcert.role="$ROLE" \
  -Dbrowser="$BROWSER"

