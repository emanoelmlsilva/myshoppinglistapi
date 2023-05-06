package entities

import org.jetbrains.annotations.NotNull
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @field:Id var email: String,
    @field:NotNull var password: String,
    @field:NotNull var name: String,
    @field:NotNull var nickName: String,
    @field:NotNull var idAvatar: Int
) {
}