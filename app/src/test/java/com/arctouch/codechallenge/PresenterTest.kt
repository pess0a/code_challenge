import com.arctouch.codechallenge.TestSetupPlugin
import com.arctouch.codechallenge.api.TmdbApi
import com.arctouch.codechallenge.infrastructure.BackendIntegrator
import com.arctouch.codechallenge.model.Movie
import com.arctouch.codechallenge.presentation.home.DetailPresenter
import com.arctouch.codechallenge.presentation.home.HomePresenter
import com.arctouch.codechallenge.repository.TmdbRepository
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class PresenterTest : TestSetupPlugin(){

//  private lateinit var tmdbRepository: TmdbRepository
var tmdbRepository: TmdbRepository = Mockito.mock(TmdbRepository::class.java)
  private lateinit var detailPresenter: DetailPresenter

  @Before
  fun setUp() {

    //tmdbRepository = TmdbRepository(BackendIntegrator.getTmdbApi())
    detailPresenter = DetailPresenter(tmdbRepository)
  }

  @Test
  fun testGetMovie() {
    val movie = Mockito.mock(Movie::class.java)

    `when`(tmdbRepository.getMovieById(1)).thenReturn(Observable.just(movie))
    val testObserver = TestObserver<Movie>()
    tmdbRepository.getMovieById(1).subscribe(testObserver)

    verify(tmdbRepository).getMovieById(1)



  }




}