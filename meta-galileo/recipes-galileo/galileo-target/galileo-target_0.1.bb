DESCRIPTION = "Quark Software Sketch Framework"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "file://galileo-target.tar.bz2 \
           file://launcher.sh \
           file://galileo-target.service"

INSTALLDIR = "/opt/cln/galileo"
FILES_${PN} += "${INSTALLDIR} \
                ${systemd_unitdir}/system/"
FILES_${PN}-dbg += "${INSTALLDIR}/.debug"

do_compile() {
	make
}

do_install() {
	oe_runmake install DESTDIR=${D}/

	install -d ${D}${sysconfdir}
	install -m 0755 ${WORKDIR}/launcher.sh ${D}${INSTALLDIR}/

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/galileo-target.service ${D}${systemd_unitdir}/system/
}

inherit systemd
SYSTEMD_SERVICE_${PN} = "galileo-target.service"
