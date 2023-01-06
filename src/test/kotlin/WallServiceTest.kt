import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class WallServiceTest {
    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

      @Test
    fun createComment_validPostId_returnComment() {
        // создаём целевой сервис
        val service = WallService
        // заполняем пост
          service.add(
              Post(
                  0,
                  2,
                  2,
                  2,
                  1,
                  "Hellow 2 ",
                  "Hellow",
                  emptyArray(),
                  Likes(1, false, false, false),
                  true,
                  emptyArray()
              )
          )

          val comment = Comments(1,1,1,true)
        // выполняем целевое действие
        val result = service.createComment(1,comment)


        // проверяем результат
        assertEquals(result.count,comment.count)
    }
    @Test(expected = PostNotFoundException::class)
    fun createComment_invalidPostId_throwPostNotFoundException() {

        val service = WallService
        service.add(
            Post(
                0,
                2,
                2,
                2,
                1,
                "Hellow 2 ",
                "Hellow",
                emptyArray(),
                Likes(1, false, false, false),
                true,
                emptyArray()
            )
        )
        // здесь код с вызовом функции, которая должна выкинуть PostNotFoundException
           service.createComment(8, Comments(1, 1, 1, true))
    }

    @Test
    fun add_postData_returnPostWithNotNullId() {
        // создаём целевой сервис
        val service = WallService
        // заполняем пост

        val addPost = Post(
            0,
            1,
            1,
            1,
            1,
            "Hellow",
            "Hellow",
            emptyArray(),
            Likes(0, false, false, false),
            true,
            emptyArray()
        )

        // выполняем целевое действие
        val result = service.add(addPost)

        // проверяем результат (используйте assertTrue или assertFalse)
       //assertTrue(result.id != 0)
       Assert.assertTrue(result.id != 0)
    }

    @Test
    fun update_incorrectId_returnFalse() {
        // создаём целевой сервис
        val service = WallService
        // заполняем несколькими постами
        service.add(
            Post(
                0,
                1,
                1,
                1,
                1,
                "Hellow",
                "Hellow",
                emptyArray(),
                Likes(0, false, false, false),
                true,
                emptyArray()
            )
        )
        service.add(
            Post(
                0,
                2,
                2,
                2,
                1,
                "Hellow 2 ",
                "Hellow",
                emptyArray(),
                Likes(1, false, false, false),
                true,
                emptyArray()
            )
        )
        // создаём информацию об обновлении
        val update = Post(
            4, 2, 2, 2, 5, "Hel ", "Hel", emptyArray(), Likes(4, false, false, false), true, emptyArray()
        )

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        Assert.assertFalse(result)
    }

    @Test
    fun update_correctId_returnTrue() {
        // создаём целевой сервис
        val service = WallService
        service.printAll()
        // заполняем несколькими постами
        service.add(
            Post(
                0,
                1,
                1,
                1,
                1,
                "Hellow",
                "Hellow",
                emptyArray(),
                Likes(0, false, false, false),
                true,
                emptyArray()
            )
        )
        service.add(
            Post(
                0,
                2,
                2,
                2,
                1,
                "Hellow 2 ",
                "Hellow",
                emptyArray(),
                Likes(1, false, false, false),
                true,
                emptyArray()
            )
        )
        // создаём информацию об обновлении
        val update = Post(
            1, 2, 2, 2, 5, "Hel ", "Hel", emptyArray(), Likes(4, false, false, false), true, emptyArray()
        )

        // выполняем целевое действие
        val result = service.update(update)

        // проверяем результат (используйте assertTrue или assertFalse)
        Assert.assertTrue(result)
    }
}