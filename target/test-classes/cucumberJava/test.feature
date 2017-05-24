@testFeature
Feature: создать программу


Scenario: создать программу
Given an open browser with https://dev.advertizoom.com/#/programs/list?sort=name&order=asc&count=50&tag=154
And я печатаю admin в поле Логин
And я печатаю 9036644342 в поле Пароль
And я нажимаю кнопку Отправить
When я создаю программу
