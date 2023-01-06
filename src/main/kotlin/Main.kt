data class Post(
    var id: Int,
    var ownerId: Int,
    var fromId: Int,
    var createdBy: Int,
    var date: Int,
    var text: String,
    var postType: String,
    var comments: Array<Comments> = emptyArray(),
    var likes: Likes?,
    var isFavorite: Boolean,
    var attachments: Array<Attachment> = emptyArray()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (fromId != other.fromId) return false
        if (createdBy != other.createdBy) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (postType != other.postType) return false
        if (likes != other.likes) return false
        if (isFavorite != other.isFavorite) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + ownerId
        result = 31 * result + fromId
        result = 31 * result + createdBy
        result = 31 * result + date
        result = 31 * result + text.hashCode()
        result = 31 * result + postType.hashCode()
        result = 31 * result + (likes?.hashCode() ?: 0)
        result = 31 * result + isFavorite.hashCode()
        return result
    }

    fun addComment(comment: Comments): Comments {
        comments += comment
        return comments.last()
    }
    fun printComments(){
        for (comment in comments) {
            println(comment)
        }
    }
}

class PostNotFoundException(message: String) : RuntimeException(message)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1
    private var comments = emptyArray<Comments>()

    fun createComment(postId: Int, comment: Comments): Comments {
        for (post in posts) {
            if (post.id == postId) {
                return post.addComment(comment)
            }
        }
        throw PostNotFoundException("No post with $postId")
    }

    fun printComment(postId: Int) {
        for (post in posts) {
            if (postId == post.id) {
                post.printComments()
                break
            }
        }
    }

    fun add(post: Post): Post {
        post.id = nextId++
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index, checkPost) in posts.withIndex()) {
            if (post.id == checkPost.id) {
                post.date = checkPost.date
                posts[index] = post
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        nextId = 1
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }

}

class Comments(
     var count: Int,
     var fromId: Int,
     var date: Int,
     var text: Boolean,
) {
    override fun toString(): String {
        return "Comments(count=$count, fromId=$fromId, date=$date, text=$text)"
    }
}

class Likes(var count: Int, var userLikes: Boolean, var canLike: Boolean, var canPublish: Boolean) {

    override fun toString(): String {
        return "Likes(count=$count, userLikes=$userLikes, canLike=$canLike, canPublish=$canPublish)"
    }

}

fun main() {
    val attachment = arrayOf<Attachment>(
        AudioAttachment(
            Audio(
                1, 1, "ttt", "ttt", 1, "ttt", 1, 1, 1,
                1, true, true
            )
        ),
        PostedPhotoAttachment(PostedPhoto(1, 1, "tt", "tt"))
    )
    val attachment2 = arrayOf<Attachment>(
        VideoAttachment(Video(1, 1, "V", "vvv", 1)),
        DocAttachment(Doc(1, 1, "doc", 120, "e"))
    )

    val post = Post(
        0, 1, 1, 1, 1, "Hellow", "Hellow", emptyArray<Comments>(), Likes(0, false, false, false), true, attachment
    )
    val post2 = Post(
        0, 2, 2, 2, 1, "Hellow 2 ", "Hellow",emptyArray<Comments>(), null, true, attachment2
    )
    WallService.add(post)
    WallService.add(post2)
    WallService.printAll()
    val attachment3 = arrayOf<Attachment>(NoteAttachment(Note(1, 1, "tit", "text", 11)))
    val post3 = Post(
        1, 2, 2, 2, 5, "Hel ", "Hel", emptyArray<Comments>(), Likes(4, false, false, false), true, attachment3
    )
    WallService.update(post3)
    println("----------------------")
    WallService.printAll()
    println("----------------------")
    try {
        WallService.createComment(1, Comments(1, 1, 1, true))
    } catch (e: PostNotFoundException) {
        println("catch mistake " + e.message)
    }
    WallService.printComment(1)
}