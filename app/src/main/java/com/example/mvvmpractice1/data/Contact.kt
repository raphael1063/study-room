package com.example.mvvmpractice1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Contact.kt
//기본키가 되는 id는 @PrimaryKey 로 지정하고, null 일 경우엔 자동으로 생성되도록 (autoGenerate = true) 속성을 주었다. 나머지 칼럼엔 @ColumnInfo 를 통해 칼럼명을 지정해주었지만, 칼럼명을 변수명과 같이 쓰려면 생략이 가능하다.
//Entity 에서 테이블 이름을 작성하는 부분인 (tableName = "contact") 부분도 위의 경우에는 클래스명과 같기 때문에 생략이 가능하다. 개인적으로는 명시하는 편이 낫다고 생각하여 함께 써주었다.
//Entity 를 만들었으면 SQL 을 작성하기 위한 DAO 인터페이스를 만들어준다.

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "number")
    var number: String,

    @ColumnInfo(name = "initial")
    var initial: Char
) {
    constructor() : this(null, "", "", '\u0000')
}

