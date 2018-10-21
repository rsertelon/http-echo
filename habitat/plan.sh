pkg_name=http-echo
pkg_origin=rsertelon
pkg_version="0.0.1-SNAPSHOT"
pkg_maintainer="Romain Sertelon <romain@sertelon.fr>"
pkg_license=("Apache-2.0")
pkg_deps=(core/jre8)
pkg_build_deps=(core/maven)
pkg_lib_dirs=(lib)

do_build() {
    pushd $PLAN_CONTEXT/..
        mvn clean install
    popd
}

do_install() {
    mkdir -p "${pkg_prefix}/lib"
    cp "$PLAN_CONTEXT/../target/${pkg_name}-${pkg_version}.jar" "${pkg_prefix}/lib/"
}