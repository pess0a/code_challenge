import com.arctouch.codechallenge.TestSetupPlugin
import com.arctouch.codechallenge.model.Genre
import com.arctouch.codechallenge.model.GenreResponse
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.model.UpcomingMoviesResponse
import com.arctouch.codechallenge.presentation.home.DetailPresenter
import com.arctouch.codechallenge.repository.TmdbRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class RepositoryTest : TestSetupPlugin(){


  @Mock
  lateinit var tmdbRepository: TmdbRepository

  @Before
  fun setUp() {
    MockitoAnnotations.initMocks(this)
  }

  @Test
  fun testGetMovie() {
    val movie = Mockito.mock(Movie::class.java)

    `when`(tmdbRepository.getMovieById(1)).thenReturn(Observable.just(movie))
    val testObserver = TestObserver<Movie>()
    tmdbRepository.getMovieById(1).subscribe(testObserver)

    verify(tmdbRepository).getMovieById(1)
  }

  @Test
  fun testGetGenres() {
    val genres = Mockito.mock(GenreResponse::class.java)

    `when`(tmdbRepository.getGenres()).thenReturn(Observable.just(genres))
    val testObserver = TestObserver<GenreResponse>()
    tmdbRepository.getGenres().subscribe(testObserver)

    verify(tmdbRepository).getGenres()

  }

  @Test
  fun testGetUpcomingMovie() {
    val upcomingMoviesResponse = Mockito.mock(UpcomingMoviesResponse::class.java)

    `when`(tmdbRepository.getUpcomingMovie(1)).thenReturn(Observable.just(upcomingMoviesResponse))
    val testObserver = TestObserver<UpcomingMoviesResponse>()
    tmdbRepository.getUpcomingMovie(1).subscribe(testObserver)

    verify(tmdbRepository).getUpcomingMovie(1)
  }



}