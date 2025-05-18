package com.passakorn.novels.domain.mapper

import com.passakorn.novels.data.model.CampaignListResponse
import com.passakorn.novels.presentation.model.CampaignItemUiModel

class CampaignListResponseMapper {

    internal fun mapToCampaignItemUiModel(response: CampaignListResponse): List<CampaignItemUiModel> {
        return response.list.map { campaign ->
            CampaignItemUiModel(
                url = campaign.payload.url,
                imageUrl = CampaignItemUiModel.ImageUrl(
                    mobile = campaign.payload.imageUrl.mobile,
                    tablet = campaign.payload.imageUrl.tablet
                )
            )
        }
    }
}