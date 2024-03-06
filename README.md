# Приложение library-control-system

## Стек технологий
* Java 19
* Spring Boot
* Gradle
* Lombok
* H2
* Docker
* Линтеры: Checkstyle, PMD
* Pre-commit hook

## Запуск приложения и его адрес

Для запуска приложения необходимо выполнить команду `./gradlew bootRun`

Адреса:
* приложение — `http://localhost:8080/`
* сваггер — `http://localhost:8080/swagger-ui/index.html`

## Линтеры

Для запуска проверки кода линтерами необходимо выполнить команду `./gradlew check`

Файлы с правилами проверок:
* [checkstyle](https://github.com/wswerlu/library-control-system/blob/master/src/main/resources/checkstyle.xml)
* [PMD](https://github.com/wswerlu/library-control-system/blob/master/src/main/resources/pmdrules.xml)

## Pre-commit hook

Для активации хука нужно установить пакет pre-commit (подробнее в [документации](https://pre-commit.com/))

Файл с настройками в проекте: [.pre-commit-config.yaml](https://github.com/wswerlu/library-control-system/blob/master/.pre-commit-config.yaml)

## GitHub actions

* [check.yml](https://github.com/wswerlu/library-control-system/blob/master/.github/workflows/check.yml) — запускает проверку линтерами, при каждом push в любую ветку репозитория
* [docker_push.yml](https://github.com/wswerlu/library-control-system/blob/master/.github/workflows/docker_push.yml) — собирает новый образ приложения и пушит его в Docker Hub, при push в мастер