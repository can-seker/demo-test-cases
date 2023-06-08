Feature: Rest API Test Cases

  Scenario: Add to Adress Request Test
    Given Base URI is "https://retoolapi.dev"
    When I send a POST request to "/bEdLxH/turknetAdressData"
      | İl      | İSTANBUL              |
      | Bina    | NO :1 3_BOTANİK BAHÇE |
      | Daire   | Ic Kapi(Daire) No :90 |
      | Sokak   | ATAŞEHİR BULVARI      |
      | İlçe    | ATAŞEHİR              |
      | Mahalle | ATATÜRK               |
    Then The response status code should be 201
    Then The response content type should be "application/json; charset=utf-8"
    Then The response body should be "İSTANBUL" and "ATAŞEHİR"
    Then The response body in the id should be not null and integer type

  Scenario: Get Adress Verify Test
    Given Base URI is "https://retoolapi.dev"
    When I send a Get request to "/bEdLxH/turknetAdressData"
    Then The response Adress body should be
      | İl      | İSTANBUL              |
      | Bina    | NO :1 3_BOTANİK BAHÇE |
      | Daire   | Ic Kapi(Daire) No :90 |
      | Sokak   | ATAŞEHİR BULVARI      |
      | İlçe    | ATAŞEHİR              |
      | Mahalle | ATATÜRK               |
