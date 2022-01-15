package es.jrhtfld.mvvm_template

import android.os.Build
import es.jrhtfld.data.ProductType
import es.jrhtfld.data.Products
import es.jrhtfld.data.value
import es.jrhtfld.data.customError
import es.jrhtfld.domain.usecase.GetProductListUseCase
import es.jrhtfld.mvvm_template.ui.base.BaseViewModel
import es.jrhtfld.mvvm_template.ui.messages.MessagesViewModel
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class ProductViewModelTest {

    private val getProductListUseCase = mockk<GetProductListUseCase>()
    private lateinit var productViewModel: MessagesViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        productViewModel = MessagesViewModel(getProductListUseCase)
        productViewModel.init()
    }

    @Test
    fun get_product_list_success() {

        coEvery { getProductListUseCase.execute() } returns value(provideProductList())

        coVerify {
<<<<<<< HEAD
            (productViewModel.conversationsLiveData.value == BaseViewModel.ScreenState.Loading)
=======
            (productViewModel.productsLiveData.value == BaseViewModel.ScreenState.LOADING)
>>>>>>> feature/agenda_events
            getProductListUseCase.execute()
            productViewModel.updateUI(success)
        }
    }

    @Test
    fun get_product_list_empty() {

        coEvery { getProductListUseCase.execute() } returns value(listOf())

        coVerify {
<<<<<<< HEAD
            (productViewModel.conversationsLiveData.value == BaseViewModel.ScreenState.Loading)
=======
            (productViewModel.productsLiveData.value == BaseViewModel.ScreenState.LOADING)
>>>>>>> feature/agenda_events
            getProductListUseCase.execute()
            productViewModel.updateUI(empty)
        }
    }

    @Test
    fun get_product_list_error() {

        coEvery { getProductListUseCase.execute() } returns customError("Error")

        coVerify {
<<<<<<< HEAD
            (productViewModel.conversationsLiveData.value == BaseViewModel.ScreenState.Loading)
=======
            (productViewModel.productsLiveData.value == BaseViewModel.ScreenState.LOADING)
>>>>>>> feature/agenda_events
            getProductListUseCase.execute()
            productViewModel.updateUI(error)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    private val success = MessagesViewModel.ConversationsState.SUCCESS(provideProductList())
    private val empty = MessagesViewModel.ConversationsState.EMPTY
    private val error = MessagesViewModel.ConversationsState.ERROR("error")
    private fun provideProductList() = listOf(
        Products(0, "Producto 1", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(1, "Producto 2", 10.00f, "", "Lorem ipsum", ProductType.FIRST),
        Products(2, "Producto 3", 10.00f, "", "Lorem ipsum", ProductType.FIRST)
    )
}