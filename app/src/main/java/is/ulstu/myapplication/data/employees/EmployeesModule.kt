package `is`.ulstu.myapplication.data.employees

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import `is`.ulstu.myapplication.base.cache.AppDatabase
import `is`.ulstu.myapplication.data.employees.sources.api.EmployeesSource
import `is`.ulstu.myapplication.data.employees.sources.api.RetrofitEmployeesSource
import `is`.ulstu.myapplication.features.employee_info.domain.EmployeeInfoRepository
import `is`.ulstu.myapplication.features.employees.domain.EmployeesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class EmployeesModule {

    @Binds
    abstract fun bindEmployeesSource(retrofitEmployeesSource: RetrofitEmployeesSource): EmployeesSource

    @Binds
    abstract fun bindEmployeesRepository(employeesRepositoryImpl: EmployeesRepositoryImpl): EmployeesRepository

    @Binds
    abstract fun bindEmployeeInfoRepository(employeesRepositoryImpl: EmployeesRepositoryImpl): EmployeeInfoRepository

    companion object {

        @Provides
        @Singleton
        fun provideEmployeesDao(appDatabase: AppDatabase) = appDatabase.employeesDao()
    }
}
