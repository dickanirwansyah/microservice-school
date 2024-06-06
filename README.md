# Stack Teknologi
1. Java spring boot (JDK  17) & 3.2.6
2. database postgresql
3. angular CLI 20.9.0 (ngb-bootstrap, bootstrap, angular material dialog)

# Goals Project
Aplikasi sekolah,
terdiri dari microservice
  a. siswa-service (melakukan pengolahan data siswa)
  b. payment-service (melakukan pengolahan pembayaran sekolah siswa)
  c. backoffice-service (melakukan pengolahan management backoffice role)
  d. discovery-service (service registry)
  e. api-gateway-service (one way api untuk merounting ke beberapa microservice)

# sourcode frontend
link project frontend : https://github.com/dickanirwansyah/frontend-school-angular

# Cara menjalankan

1. eksekusi script migration-script.sql di masing-masing microservice
2. jalankan discovery service (eureka)
3. jalankan masing-masing microservice `mvn clean install spring-boot:run`
4. jalankan frontend angular `npm run start`

# screenshoot project

   1. # Discovery service

      ![Screenshot from 2024-06-06 22-42-43](https://github.com/dickanirwansyah/microservice-school/assets/24214234/c1a2ee44-0d5f-49da-a655-c8af0001e311)
      
   2. # Frontend Halaman login
      
      ![Screenshot from 2024-06-06 22-41-54](https://github.com/dickanirwansyah/microservice-school/assets/24214234/278f9d44-7388-4ef6-9942-1b0ae9eaf2f2)

   3. # Backoffice 

      ![Screenshot from 2024-06-06 22-46-31](https://github.com/dickanirwansyah/microservice-school/assets/24214234/ec1a8bb9-d893-4b2f-98e9-8032f8986855)

      ![Screenshot from 2024-06-06 22-47-16](https://github.com/dickanirwansyah/microservice-school/assets/24214234/24abb4d5-1e78-4ca7-8720-516aba577d93)

      ![Screenshot from 2024-06-06 22-47-41](https://github.com/dickanirwansyah/microservice-school/assets/24214234/3fe421ed-760e-42ab-892f-52a1271b901b)



   
