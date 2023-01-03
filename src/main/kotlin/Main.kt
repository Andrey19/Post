
data class Post(
     var id: Int,
     var ownerId: Int,
     var fromId: Int,
     var createdBy: Int,
     var date: Int,
     var text: String,
     var postType: String,
     var comments: Comments?,
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
        if (comments != other.comments) return false
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
        result = 31 * result + (comments?.hashCode() ?: 0)
        result = 31 * result + (likes?.hashCode() ?: 0)
        result = 31 * result + isFavorite.hashCode()
        return result
    }
}

object WallService{
    private var posts = emptyArray<Post>()
    private var nextId = 1
    fun add(post: Post): Post{
        post.id = nextId++
        posts += post
        return posts.last()
    }

    fun update(post: Post): Boolean {
        for ((index,checkPost) in posts.withIndex()){
            if (post.id == checkPost.id){
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
    fun printAll(){
        for (post in posts){
            println(post)
        }
    }

}
class Comments(
    private var count: Int,
    private var canPost: Boolean,
    private var groupsCanPost: Boolean,
    private var canClose: Boolean,
    private var canOpen: Boolean
) {

    override fun toString(): String {
        return "Comments(count=$count, canPost=$canPost, groupsCanPost=$groupsCanPost, canClose=$canClose, canOpen=$canOpen)"
    }


}

class Likes(var count: Int, var userLikes: Boolean, var canLike: Boolean, var canPublish: Boolean) {

    override fun toString(): String {
        return "Likes(count=$count, userLikes=$userLikes, canLike=$canLike, canPublish=$canPublish)"
    }

}

fun main() {
    val attachment = arrayOf<Attachment>(AudioAttachment("audio",Audio(1,1,"ttt","ttt",1,"ttt",1,1,1,
        1,true,true)),
        PostedPhotoAttachment("posted_photo",PostedPhoto(1,1,"tt","tt"))
    )
    val attachment2 = arrayOf<Attachment>(VideoAttachment("video",Video(1,1,"V","vvv",1)),
        DocAttachment("doc",Doc(1,1,"doc",120,"e"))
        )

    val post = Post(0,1,1,1,1
        ,"Hellow","Hellow"
        ,null
        ,Likes(0,false,false,false),true, attachment
    )
    val post2 = Post(0,2,2,2,1
        ,"Hellow 2 ","Hellow"
        ,Comments(1,false,false,false,false)
        ,null,true,attachment2)
    WallService.add(post)
    WallService.add(post2)
    WallService.printAll()
    val attachment3 = arrayOf<Attachment>(NoteAttachment("note",Note(1,1,"tit","text",11)))
    val post3 = Post(1,2,2,2,5
        ,"Hel ","Hel"
        ,Comments(4,false,false,false,false)
        ,Likes(4,false,false,false),true, attachment3
    )
    WallService.update(post3)
    println("----------------------")
    WallService.printAll()

}