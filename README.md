# Lab 04 — JUnit 5 нэгжийн тест

## 

- Нэр: Г.Саранчимэг
- оюутны код: B232274001

## Орчин

`java -version` командын бүтэн гаралт:

```text
openjdk version "17.0.16" 2025-07-15
OpenJDK Runtime Environment Temurin-17.0.16+8 (build 17.0.16+8)
OpenJDK 64-Bit Server VM Temurin-17.0.16+8 (build 17.0.16+8, mixed mode, sharing)
```

`mvn -version` командын бүтэн гаралт:

```text
Apache Maven 3.10.1
Maven home: C:\Program Files\Apache\Maven\apache-maven-3.10.1
Java version: 17.0.16, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot
Default locale: en_US, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

JUnit Jupiter: 5.10.2  
Surefire: 3.2.5

```text
```

Төслийн код нь `GradeCalculator` классын үсгэн дүн болон 100 онооны жингийн нийлбэрийг тооцно. `letterGrade` нь 90+ → A, 80–89 → B, 70–79 → C, 60–69 → D, 0–59 → F дүрмийг хэрэглэнэ. Бүх оролтыг өөрийн зөвшөөрөгдсөн хязгаарт шалгаж, буруу утгад `IllegalArgumentException` шиднэ.

## Ажиллуулах

```bash
mvn test 2>&1 | tee results/mvn-test.txt
```

`target/` нь `.gitignore`-д орсон бөгөөд `results/` доторх тайлангуудыг хадгална.

## Тестийн хамрах хүрээ

Тестүүд Arrange–Act–Assert бүтэцтэй, `@DisplayName`-тай бөгөөд энгийн утга, хязгаарын утга (0, 60, 89.99, 90, 100), буруу сөрөг болон дээд хязгаараас давсан оролтыг шалгана. Нийт 10 тестийн методоос гадна `@ParameterizedTest`-ийн мөрүүд тус бүрээр ажиллана. `results/mvn-test.txt`-д 23 тест ажиллаж, `Failures: 0`, `Errors: 0`, `BUILD SUCCESS` гарсан.

## Mutation testing

`letterGrade` доторх `score >= 90` нөхцөлийг түр хугацаанд `score > 90` болгон өөрчилж дараах командыг ажиллуулсан.

```bash
mvn test 2>&1 | tee results/mvn-test-mutant.txt
```

90 оноо A байх ёстой гэсэн тест уг мутацийг илрүүлж, тестийн алдаа болон `BUILD FAILURE` гарсан. Мутацид унасан тестүүд нь `ninetyIsExactlyA` болон `letterGradeBoundaries` юм. Хоёуланд нь гарсан мессеж `expected: <A> but was: <B>` байсан. `letterGradeBoundaries`-ийн уналт нь параметрчилсэн тестийн `[2]` мөрөнд бүртгэгдсэн. Энэ нь `90` онооны заагийг `>` болгож өөрчилбөл яг `90` нь A биш B болж буруу ангилагддагийг харуулсан. Нөхцөлийг `score >= 90` болгон сэргээсний дараах үндсэн үр дүн `Tests run: 23, Failures: 0, Errors: 0, Skipped: 0`, `BUILD SUCCESS` байв. Иймээс энэ заагийн тест нь жижиг нөхцөлийн өөрчлөлт бодит зан төлөвийг хэрхэн эвддэгийг хамгийн тодорхой харуулсан.

## AI-ийн тусламжийн дүгнэлт

AI нь ердийн дүнгийн ангилал, бүх заагийн утга, сөрөг болон дээд хязгаараас давсан оролт, нийт онооны хязгаарыг санал болгосон. Анхны саналд зарим заагийн утга болон `totalScore`-ийн тусдаа буруу оролтын шалгалт дутуу байсан. Тиймээс 90, 89.99, 60, 59.99, 0, 100 утгуудыг нэмж, `att = -5`, `lab = 41`, `exam = 31` тохиолдлуудад `assertThrows` ашигласан. Parameterized тестийн мөр бүр тусдаа тест болж ажилладгийг мөн тооцсон. Эцсийн тестийн тоог Maven-ийн бодит тайлангаас шалгана.

Эцсийн Maven тайланд 23 тест ажиллаж, алдаа болон failure бүртгэгдээгүй.

## Лиценз

Энэ лабораторид ашигласан JUnit 5, Maven болон OpenJDK нь үнэгүй, нээлттэй эхийн хэрэгслүүд юм.
