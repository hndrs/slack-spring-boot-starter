import io.hndrs.publish.meta.Contributor
import io.hndrs.publish.meta.Developer
import io.hndrs.publish.meta.License
import io.hndrs.publish.meta.Organization
import io.hndrs.publish.meta.Scm

extra["developers"] = listOf(
    Developer("marvinschramm", "Marvin Schramm", "marvin.schramm@gmail.com"),
    Developer("bendenger", "Ben Denger", "ben.denger.bd@gmail.com")
)
extra["contributors"] = listOf(
    Contributor("Jascha Ostholt", "jascha.ostholt@gmail.com"),
    Contributor("Volodymyr Abaza", "abaza.org@gmail.com"),
    Contributor("Alexander Militzer", "alex.militzer@gmail.com")
)
extra["organization"] = Organization("hndrs", "https://oss.hndrs.io")
extra["scm"] = Scm("scm:git:git://github.com/hndrs/slack-spring-boot-starter.git", "https://github.com/hndrs/slack-spring-boot-starter")
extra["license"] = License("http://www.opensource.org/licenses/mit-license.php", "MIT License")
