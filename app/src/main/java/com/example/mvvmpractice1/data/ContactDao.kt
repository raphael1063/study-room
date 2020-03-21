package com.example.mvvmpractice1.data

import androidx.lifecycle.LiveData
import androidx.room.*

// ContactDao.kt
//@Query, @Insert, @Update, @Delete 등의 어노테이션을 제공한다. 또한 Insert 와 Update 에서는 onConflict 속성을 지정할 수 있다. 중복된 데이터의 경우 어떻게 처리할 것인지에 대한 처리를 지정할 수 있다.
//OnConflictStrategy 인터페이스를 호출해 REPLACE, IGNORE, ABORT, FAIL, ROLLBACK 등의 액션이 지정 가능하다.
//또, 주목할 것은 전체 연락처 리스트를 반환하는 getAll 함수를 만들 때 LiveData 를 반환해준다는 점이다. 기존의 익숙한 List<Contact> 형식에 LiveData<>를 감싸주는 방식으로 만들 수 있다.
@Dao
interface ContactDao {


    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAll(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE) //onConflict = OnConflictStrategy.REPLACE 는 Insert 할때 PrimaryKey 가 겹치는 것이 있으면 덮어 쓴다는 의미이다.
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

}