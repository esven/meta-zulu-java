PV_UPDATE = "91"
BUILD_NUMBER = "8.14.0.6"

SUMMARY = "Azul Zulu Java Development Kit (JDK) binaries"
DESCRIPTION = "This the Embedded JDK for the ARM architecture from Azul \
 Systems Inc. It is created using OpenJDK code, which is licensed under \
 GPLv2 and various other third party licenses. Azul offers a variety of \
 support plans, as well as patent indemnification and guarantees against \
 the risk of open source viral contamination, as part of its Zulu \
 Embedded commercial offerings."

BBCLASSEXTEND = "native"

LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32/LICENSE;md5=7b4baeedfe2d40cb03536573bc2c89b1 \
	file://ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32/DISCLAIMER;md5=bf091aab038f6cf58cd341766b1d3d8d \
	file://ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32/THIRD_PARTY_README;md5=745d6db5fc58c63f74ce6a7d4db7e695"

SRC_URI="http://cdn.azul.com/zulu-embedded/bin/ezdk-1.8.0_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32.tar.gz"

SRC_URI[md5sum] = "93ae43a706bb4583b56759cce8256e6b"
SRC_URI[sha256sum] = "ad204157dd34fe95c8dd3a0b83b6b1a3327019b90d2c14f33bd151917a5ad78a"

PR =. "u${PV_UPDATE}"
S = "${WORKDIR}"

do_install () {
  install -d -m 0755 ${D}${datadir}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32
  cp -a ${S}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32 ${D}${datadir}/
  install -d -m 0755 ${D}${bindir}
  ln -sf ${datadir}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32/bin/java ${D}${bindir}/java
}

# All the files are provided in a binaray package, and keeping all the
# files in a single package causes packaging QA errors and warnings.
# Avoid these packaging failure by skiping all the QA checks
INSANE_SKIP_${PN} = "${ERROR_QA} ${WARN_QA}"

FILES_${PN}-dbg = "${datadir}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32/jre/lib/aarch32/.debug/ \
	${datadir}/ezdk-${PV}_${PV_UPDATE}-${BUILD_NUMBER}-linux_aarch32/jdemo/jvmti/*/lib/.debug/"
FILES_${PN} = "/usr/"
RPROVIDES_${PN} = "java2-runtime java2-vm"
PROVIDES += "virtual/java"

COMPATIBLE_MACHINE = "arm"
