# 📱 QR Mine

**QR Mine**은 내가 만든 QR, 내가 관리하는 QR이라는 철학을 바탕으로, **직접 만든 QR 코드들을 생성/저장/공유/관리**할 수 있는 간편한 모바일 앱입니다.

---

## ✨ 주요 기능

- QR 코드 생성
- QR 이미지 저장 기능

---

## 🛠 기술 스택

### Android
- **언어**: Kotlin
- **UI**: Jetpack Compose
- **상태 관리**: ViewModel + Compose State
- **의존성 주입**: Hilt
- **Navigation**: Jetpack Navigation Component
- **QR 생성**: ZXing 라이브러리 사용
- **Local DB**: Room (QR 정보 저장)
- **디자인 시스템**: Material3 기반 테마 + Custom Color Scheme

---

## 📦 프로젝트 구조

```
qr-mine/
├── app/                       # 메인 안드로이드 앱
│   ├── ui/                    # Compose 기반 UI 화면 구성
│   ├── data/                  # Room DB, 모델 클래스
│   ├── viewmodel/            # 상태 및 UI 로직 관리
│   ├── di/                    # Hilt 모듈 구성
│   ├── util/                  # QR 생성, 시간 변환 등 유틸
│   └── MainActivity.kt       # 진입점
└── build.gradle.kts          # Gradle 설정 (KTS 기반)
```
---

## 📌 향후 개선 예정
- [ ] 다크모드 대응
- [ ] QR 스캔 기능 추가 (내 QR 비교용)
- [ ] Cloud 백업/복원 기능

---

## 👩‍💻 개발자
- **신가영** ([GitHub](https://github.com/gay00ung))

> 본 앱은 개인 프로젝트로 개발되어 [Google Play 스토어](https://play.google.com/store/apps/details?id=net.ifmain.qr_mine)에 실제 출시되었습니다.
