interface Attachment {
    val type: String
}

class Note(
    var id: Int,
    var ownerId: Int,
    var title: String,
    var text: String,
    var date: Int
) {
    override fun toString(): String {
        return "Note(id=$id, ownerId=$ownerId, title='$title', text='$text', date=$date)"
    }
}

class NoteAttachment(val note: Note) : Attachment {
    override val type: String = "note"
    override fun toString(): String {
        return "NoteAttachment(type='$type', note=$note)"
    }
}

class Doc(
    var id: Int,
    var ownerId: Int,
    var title: String,
    var size: Int,
    var ext: String
) {
    override fun toString(): String {
        return "Doc(id=$id, ownerId=$ownerId, title='$title', size=$size, ext='$ext')"
    }
}

class DocAttachment(val doc: Doc) : Attachment {
    override val type: String = "doc"
    override fun toString(): String {
        return "DocAttachment(type='$type', doc=$doc)"
    }
}

class Video(
    var id: Int,
    var ownerId: Int,
    var title: String,
    var description: String,
    var duration: Int
) {
    override fun toString(): String {
        return "Video(id=$id, ownerId=$ownerId, title='$title', description='$description', duration='$duration')"
    }
}

class VideoAttachment(val video: Video) : Attachment {
    override val type: String = "video"
    override fun toString(): String {
        return "VideoAttachment(type='$type', video=$video)"
    }
}

class PostedPhoto(
    var id: Int,
    var ownerId: Int,
    var photo130: String,
    var photo604: String
) {
    override fun toString(): String {
        return "PostedPhoto(id=$id, ownerId=$ownerId, photo130='$photo130', photo604='$photo604')"
    }
}

class PostedPhotoAttachment(val postedPhoto: PostedPhoto) : Attachment {
    override val type: String = "posted_photo"
    override fun toString(): String {
        return "PostedPhotoAttachment(type='$type', postedPhoto=$postedPhoto)"
    }
}

class Audio(
    var id: Int,
    var ownerId: Int,
    var artist: String,
    var title: String,
    var duration: Int,
    var url: String,
    var lyricsId: Int,
    var albumId: Int,
    var genreId: Int,
    var date: Int,
    var noSearch: Boolean,
    var isHq: Boolean
) {
    override fun toString(): String {
        return "Audio(id=$id, ownerId=$ownerId, artist='$artist', title='$title', duration=$duration, url='$url', lyricsId=$lyricsId, albumId=$albumId, genreId=$genreId, date=$date, noSearch=$noSearch, isHq=$isHq)"
    }
}

class AudioAttachment(val audio: Audio) : Attachment {
    override val type: String = "audio"
    override fun toString(): String {
        return "AudioAttachment(type='$type', audio=$audio)"
    }
}
