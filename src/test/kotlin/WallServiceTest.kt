import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.DefaultAsserter.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class WallServiceTest {
    @BeforeEach
    fun clearBeforeTest() {
        WallService.clear()
    }
    @Test
    fun add_postData_returnPostWithNotNullId() {
        // создаём целевой сервис
        val service = WallService
        // заполняем пост

        val addPost = Post(0,1,1,1,1
            ,"Hellow","Hellow"
            ,Comments(0,false,false,false,false)
            ,Likes(0,false,false,false),true)

        // выполняем целевое действие
        val result = service.add(addPost)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result.id != 0)
    }

    @Test
    fun update_incorrectId_returnFalse() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add( Post(0,1,1,1,1
            ,"Hellow","Hellow"
            ,Comments(0,false,false,false,false)
            ,Likes(0,false,false,false),true))
        service.add(Post(0,2,2,2,1
            ,"Hellow 2 ","Hellow"
            ,Comments(1,false,false,false,false)
            ,Likes(1,false,false,false),true))
        // создаём информацию об обновлении
        val update = Post(4,2,2,2,5
            ,"Hel ","Hel"
            ,Comments(4,false,false,false,false)
            ,Likes(4,false,false,false),true)

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertFalse(result)
    }

    @Test
    fun update_correctId_returnTrue() {
        // создаём целевой сервис
        val service = WallService
        service.printAll()
        // заполняем несколькими постами
        service.add( Post(0,1,1,1,1
            ,"Hellow","Hellow"
            ,Comments(0,false,false,false,false)
            ,Likes(0,false,false,false),true))
        service.add(Post(0,2,2,2,1
            ,"Hellow 2 ","Hellow"
            ,Comments(1,false,false,false,false)
            ,Likes(1,false,false,false),true))
        // создаём информацию об обновлении
        val update = Post(1,2,2,2,5
            ,"Hel ","Hel"
            ,Comments(4,false,false,false,false)
            ,Likes(4,false,false,false),true)

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        assertTrue(result)
    }
}