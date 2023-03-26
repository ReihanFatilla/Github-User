package com.reift.githubuser.presentation.detail

import com.reift.core.domain.entity.followuser.FollowUser
import com.reift.core.domain.usecase.detail.DetailUseCase
import com.reift.githubuser.utils.Dummy
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class DetailViewModelTest {

    @Mock
    private lateinit var detailUseCase: DetailUseCase

    private lateinit var detailViewModel: DetailViewModel

    private var dummyDetail = Dummy.generateDetail()
    private var dummyFollow = Dummy.generateFollow()
    private var dummyUser = Dummy.generateUser()

    private var usn = "reihanfatilla"

    @Before
    fun setUp(){
        detailViewModel = DetailViewModel(detailUseCase)
    }

    @Test
    fun `when get detail should not return null and return success`() {
        val expectedDetail = Flowable.just(dummyDetail)

        `when`(detailUseCase.getUserDetail(usn)).thenReturn(expectedDetail)

        detailViewModel.getUserDetail(usn).subscribe { actualDetail ->
            Mockito.verify(detailUseCase).getUserDetail(usn)
            assertNotNull(actualDetail)
            assertEquals(dummyDetail, actualDetail)
        }
    }

    @Test
    fun `when get followers should not return null and return success`() {
        val expectedFollowers = Flowable.just(dummyFollow)

        `when`(detailUseCase.getUserFollowers(usn)).thenReturn(expectedFollowers)
        detailViewModel.getUserFollowers(usn).subscribe { actualDetail ->
            Mockito.verify(detailUseCase).getUserFollowers(usn)
            assertNotNull(actualDetail)
            assertEquals(dummyFollow, actualDetail)
        }
    }

    @Test
    fun `when get following should not return null and return success`() {
        val expectedFollowing = Flowable.just(dummyFollow)

        `when`(detailUseCase.getUserFollowing(usn)).thenReturn(expectedFollowing)
        detailViewModel.getUserFollowing(usn).subscribe { actualDetail ->
            Mockito.verify(detailUseCase).getUserFollowing(usn)
            assertNotNull(actualDetail)
            assertEquals(dummyFollow, actualDetail)
        }
    }

    @Test
    fun `when inserting followUser should call addFollowUser on DetailUseCase`() {
        detailViewModel.insertFollowing(dummyUser)
        Mockito.verify(detailUseCase).addFollowUser(dummyUser)
    }

    @Test
    fun `when removing followUser should call deleteFollowUser on DetailUseCase`() {
        detailViewModel.deleteFollowing(dummyUser)
        Mockito.verify(detailUseCase).deleteFollowUser(dummyUser)
    }
}