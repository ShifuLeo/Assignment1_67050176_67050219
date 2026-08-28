# My Memory Animation

แอนิเมชัน 2 มิติจากรายวิชา Computer Graphic ที่เขียนขึ้นด้วย Java AWT/Swing พื้นฐาน[cite: 4]
ทำงานด้วยการพล็อตแกน x, y ทีละพิกเซลโดยไม่ใช้ไลบรารีสำเร็จรูป[cite: 4]

> รูปภาพแรงบันดาลใจทั้งหมดดูได้ที่โฟลเดอร์ `inspire_image/` และวิดีโอผลลัพธ์การรันอยู่ที่ `Video/`

## Tech Stack

| ส่วน | เทคโนโลยี |
|---|---|
| ภาษากราฟิกหลัก | Java Graphic 2D, GraphicsSwing[cite: 4] |
| อัลกอริทึมวาดภาพ | Bresenham Line, Bezier Curve, Midpoint Circle/Ellipse, Flood Fill[cite: 1, 2] |
| ระบบเสียง | `javax.sound.sampled` |
| การแสดงผล | `JFrame`, `JPanel`, `BufferedImage` (Double Buffering) |

## โครงสร้างโปรเจกต์

โปรเจกต์ถูกแบ่งออกเป็น 3 โฟลเดอร์หลัก:

1. **`Assignment/`** — โฟลเดอร์หลักที่เก็บซอร์สโค้ด Java และไฟล์ Assets (รูปภาพ, เสียง)
2. **`inspire_image/`** — เก็บรูปภาพ Reference และภาพสตอรี่บอร์ดที่เป็นแรงบันดาลใจ
3. **`Video/`** — วิดีโอบันทึกผลลัพธ์การรันแอนิเมชันฉบับสมบูรณ์

## โครงสร้างไฟล์ในโฟลเดอร์ Assignment

**Core Engine & Controller**
- `Main.java` — ไฟล์หลักสำหรับสั่งรันโปรแกรม ควบคุมการเปลี่ยนฉาก และเล่นเพลงประกอบ `sound.wav`
- `JavaSwing.java` — คลาสเก็บสมการคณิตศาสตร์คอมพิวเตอร์กราฟิกสำหรับวาดรูปทรงและเทสี

**Scene 1 (ความทรงจำในอดีต)**
- `Scene1.java` — ตัวควบคุมฉากแรก (แอนิเมชันเมฆเคลื่อนที่, เทปหมุน, แมวส่ายหาง)[cite: 1, 3]
- `Background01.java` — ฉากหลังวิวภูเขา ท้องฟ้า และตลับเทป
- `Cat1.java` — แอนิเมชันน้องแมวสีดำ (ขยับหางและกะพริบตา)

**Scene 2 (ปัจจุบันและความคิดถึง)**
- `Scene2.java` — ตัวควบคุมฉากที่สอง (แอนิเมชันดาวกะพริบ, สลับเฟรมผู้หญิงร้องไห้, วิญญาณแมว)
- `Background02.java` — ฉากหลังห้องนอนยามค่ำคืน หน้าต่าง และการโหลดรูปภาพเข้ากรอบรูป[cite: 1]
- `Girl.java` — ตัวละครผู้หญิงนั่งร้องไห้คิดถึงแมว[cite: 1]
- `CatSpirit.java` — วิญญาณน้องแมวสีฟ้ากึ่งโปร่งแสงที่คอยอยู่เคียงข้าง[cite: 1]

**Assets**
- `catPic01.png`, `catPic03.png`, `catPic04.png` — รูปภาพน้องแมวของจริงที่โหลดเข้าไปในกรอบรูป[cite: 1]
- `sound.wav` — ไฟล์ดนตรีประกอบแอนิเมชัน

## Prerequisites

- Java Development Kit (JDK) 8 หรือใหม่กว่า
- Command Line หรือ Terminal

## Getting Started

### 1. Clone โปรเจกต์

```bash
git clone https://github.com/ShifuLeo/Assignment1_67050176_67050219.git
cd <repo-folder>/Assignment
```

### 2. คอมไพล์โค้ด

```bash
# สำหรับ Windows
del *.class
javac *.java

# สำหรับ macOS/Linux
rm -f *.class
javac *.java
```

### 3. รันแอนิเมชัน

```bash
javac Main.java
java Main
```
