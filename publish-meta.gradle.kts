import com.kreait.publish.meta.Contributor
import com.kreait.publish.meta.Developer
import com.kreait.publish.meta.License
import com.kreait.publish.meta.Organization
import com.kreait.publish.meta.Scm

extra["developers"] = listOf(
        Developer("marvinschramm", "Marvin Schramm", "marvin.schramm@gmail.com"),
        Developer("bendenger", "Ben Denger", "ben.denger.bd@gmail.com"),
        Developer("andakawa", "Alexander Militzer", "alex.militzer@gmail.com")
)
extra["contributors"] = listOf(Contributor("Jascha Ostholt", "jascha.ostholt@gmail.com"))
extra["organization"] = Organization("kreait GmbH", "https://kreait.com")
extra["scm"] = Scm("scm:git:git://github.com/kreait/spring-boot-starter.git", "https://github.com/kreait/slack-spring-boot-starter")
extra["license"] = License("http://www.opensource.org/licenses/mit-license.php", "MIT License")
