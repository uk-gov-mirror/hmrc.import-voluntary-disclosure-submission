/*
 * Copyright 2021 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package services

import base.SpecBase
import connectors.MockImporterAddressConnector
import org.scalatest.matchers.must.Matchers.convertToAnyMustWrapper
import play.api.test.Helpers.{await, defaultAwaitTimeout}
import utils.ReusableValues

import scala.concurrent.ExecutionContext.Implicits.global


class ImporterAddressServiceSpec extends SpecBase with MockImporterAddressConnector with ReusableValues {

  def setup(traderAddressResponse: TraderAddressResponse): ImporterAddressService = {
    setupMockGetAddress(traderAddressResponse)
    new ImporterAddressService(mockAddressLookupConnector)
  }

  "connector call is successful" should {
    lazy val service = setup(Right(traderAddress))
    lazy val result = service.retrieveAddress(idOne)

    "return successful RetrieveAddressResponse" in {
      await(result) mustBe Right(traderAddress)
    }
  }
}
