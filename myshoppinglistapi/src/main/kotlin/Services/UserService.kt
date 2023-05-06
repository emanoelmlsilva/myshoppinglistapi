package Services

import entities.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import repositories.UserRepository

@Service
class UserService() {

    @Autowired
    lateinit var userRepository: UserRepository

    fun findAllUser(): List<User>{
        return userRepository.findAll()
    }

    fun saveUser(user: User){
        userRepository.save(user)
    }
}