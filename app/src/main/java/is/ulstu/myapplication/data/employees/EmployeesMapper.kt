package `is`.ulstu.myapplication.data.employees

import `is`.ulstu.myapplication.data.employees.sources.api.entities.EmployeeRateResponseEntity
import `is`.ulstu.myapplication.data.employees.sources.api.entities.EmployeeResponseEntity
import `is`.ulstu.myapplication.data.employees.sources.api.entities.RateResponseEntity
import `is`.ulstu.myapplication.data.employees.sources.api.entities.RatedValueResponseEntity
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.EmployeeEntity
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.EmployeeRateEntity
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.RateEntity
import `is`.ulstu.myapplication.data.employees.sources.cache.entities.RatedValueEntity
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeModel
import `is`.ulstu.myapplication.features.employees.domain.models.EmployeeRateModel
import `is`.ulstu.myapplication.features.employees.domain.models.RateModel
import `is`.ulstu.myapplication.features.employees.domain.models.RatedValueModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EmployeesMapper @Inject constructor() {

    fun RateResponseEntity.toRateModel() = RateModel.valueOf(value = name)

    fun RatedValueResponseEntity.toRatedValueModel() = RatedValueModel(
        value = value,
        rate = rate.toRateModel(),
    )

    fun EmployeeRateResponseEntity.toEmployeeRateModel() = EmployeeRateModel(
        teamWork = teamWork?.toRatedValueModel(),
        leadership = leadership?.toRatedValueModel(),
        responsibility = responsibility?.toRatedValueModel(),
        employeesRate = employeesRate?.toRatedValueModel(),
        clientsRate = clientsRate?.toRatedValueModel(),
        bossRate = bossRate?.toRatedValueModel(),
        organizationWorkProcess = organizationWorkProcess?.toRatedValueModel(),
        punctuality = punctuality?.toRatedValueModel(),
        stressResistance = stressResistance?.toRatedValueModel(),
        creativity = creativity?.toRatedValueModel(),
        analyticsSkills = analyticsSkills?.toRatedValueModel(),
        communicationsSkills = communicationsSkills?.toRatedValueModel(),
    )

    fun EmployeeResponseEntity.toEmployeeModel() = EmployeeModel(
        id = id,
        fullName = fullName,
        city = city,
        birthDate = birthDate,
        department = department,
        position = position,
        seniority = seniority,
        hardSkills = hardSkills,
        softSkills = softSkills,
        techs = techs,
        hrRecommendations = hrRecommendations,
        achievements = achievements,
        salary = salary,
        rate = rate.toEmployeeRateModel(),
    )


    fun RateModel.toRateEntity() = RateEntity.valueOf(value = name)

    fun RatedValueModel.toRatedValueEntity() = RatedValueEntity(
        value = value,
        rate = rate.toRateEntity(),
    )

    fun EmployeeRateModel.toEmployeeRateEntity() = EmployeeRateEntity(
        teamWork = teamWork?.toRatedValueEntity(),
        leadership = leadership?.toRatedValueEntity(),
        responsibility = responsibility?.toRatedValueEntity(),
        employeesRate = employeesRate?.toRatedValueEntity(),
        clientsRate = clientsRate?.toRatedValueEntity(),
        bossRate = bossRate?.toRatedValueEntity(),
        organizationWorkProcess = organizationWorkProcess?.toRatedValueEntity(),
        punctuality = punctuality?.toRatedValueEntity(),
        stressResistance = stressResistance?.toRatedValueEntity(),
        creativity = creativity?.toRatedValueEntity(),
        analyticsSkills = analyticsSkills?.toRatedValueEntity(),
        communicationsSkills = communicationsSkills?.toRatedValueEntity(),
    )

    fun EmployeeModel.toEmployeeEntity() = EmployeeEntity(
        id = id,
        fullName = fullName,
        city = city,
        birthDate = birthDate,
        department = department,
        position = position,
        seniority = seniority,
        hardSkills = hardSkills,
        softSkills = softSkills,
        techs = techs,
        hrRecommendations = hrRecommendations,
        achievements = achievements,
        salary = salary,
        rate = rate.toEmployeeRateEntity(),
    )


    fun RateEntity.toRateModel() = RateModel.valueOf(value = name)

    fun RatedValueEntity.toRatedValueModel() = RatedValueModel(
        value = value,
        rate = rate.toRateModel(),
    )

    fun EmployeeRateEntity.toEmployeeRateModel() = EmployeeRateModel(
        teamWork = teamWork?.toRatedValueModel(),
        leadership = leadership?.toRatedValueModel(),
        responsibility = responsibility?.toRatedValueModel(),
        employeesRate = employeesRate?.toRatedValueModel(),
        clientsRate = clientsRate?.toRatedValueModel(),
        bossRate = bossRate?.toRatedValueModel(),
        organizationWorkProcess = organizationWorkProcess?.toRatedValueModel(),
        punctuality = punctuality?.toRatedValueModel(),
        stressResistance = stressResistance?.toRatedValueModel(),
        creativity = creativity?.toRatedValueModel(),
        analyticsSkills = analyticsSkills?.toRatedValueModel(),
        communicationsSkills = communicationsSkills?.toRatedValueModel(),
    )

    fun EmployeeEntity.toEmployeeModel() = EmployeeModel(
        id = id,
        fullName = fullName,
        city = city,
        birthDate = birthDate,
        department = department,
        position = position,
        seniority = seniority,
        hardSkills = hardSkills,
        softSkills = softSkills,
        techs = techs,
        hrRecommendations = hrRecommendations,
        achievements = achievements,
        salary = salary,
        rate = rate.toEmployeeRateModel(),
    )
}
