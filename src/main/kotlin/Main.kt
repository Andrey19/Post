

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy: Int,
    val date: Int,
    val text: String,
    val postType: String,
    val comments: Comments,
    val likes: Likes,
    val isFavorite: Boolean
)

object WallService{
    private var posts = emptyArray<Post>()
    private var nextId = 1
    fun add(post: Post): Post{
        posts += post.copy(id = nextId++)
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index,checkPost) in posts.withIndex()){
            if (post.id == checkPost.id){
                posts[index] = post.copy(id = checkPost.id, date = checkPost.date)
                return true
            }
        }
        return false
    }
    fun clear() {
        posts = emptyArray()
        nextId = 1
    }
    fun printAll(){
        for (post in posts){
            println(post)
        }
    }

}
class Comments {
    private var count: Int = 0
    private var canPost: Boolean = false
    private var groupsCanPost: Boolean = false
    private var canClose: Boolean = false
    private var canOpen: Boolean = false

    constructor(count: Int, canPost: Boolean, groupsCanPost: Boolean, canClose: Boolean, canOpen: Boolean) {
        this.count = count
        this.canPost = canPost
        this.groupsCanPost = groupsCanPost
        this.canClose = canClose
        this.canOpen = canOpen
    }

    override fun toString(): String {
        return "Comments(count=$count, canPost=$canPost, groupsCanPost=$groupsCanPost, canClose=$canClose, canOpen=$canOpen)"
    }


}

class Likes {
    var count: Int = 0
    var userLikes: Boolean = false
    var canLike: Boolean = false
    var canPublish: Boolean = false

    constructor(count: Int, userLikes: Boolean, canLike: Boolean, canPublish: Boolean) {
        this.count = count
        this.userLikes = userLikes
        this.canLike = canLike
        this.canPublish = canPublish
    }

    override fun toString(): String {
        return "Likes(count=$count, userLikes=$userLikes, canLike=$canLike, canPublish=$canPublish)"
    }

}

fun main(args: Array<String>) {
    val post = Post(0,1,1,1,1
        ,"Hellow","Hellow"
        ,Comments(0,false,false,false,false)
        ,Likes(0,false,false,false),true)
    val post2 = Post(0,2,2,2,1
        ,"Hellow 2 ","Hellow"
        ,Comments(1,false,false,false,false)
        ,Likes(1,false,false,false),true)
    WallService.add(post)
    WallService.add(post2)
    WallService.printAll()
    val post3 = Post(1,2,2,2,5
        ,"Hel ","Hel"
        ,Comments(4,false,false,false,false)
        ,Likes(4,false,false,false),true)
    WallService.update(post3)
    println("----------------------")
    WallService.printAll()
}