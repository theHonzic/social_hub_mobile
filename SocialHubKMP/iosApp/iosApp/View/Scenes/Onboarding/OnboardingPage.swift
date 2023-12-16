//
//  OnboardingPage.swift
//  iosApp
//
//  Created by Jan Janovec on 16.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared
import SwiftUI

struct OnboardingPage: View {
    var item: OnboardingContractOnboardingStep
    var body: some View {
        VStack {
            Image("try")
                .resizable()
                .scaledToFit()
            Spacer()
            VStack(alignment: .leading, spacing: 8) {
                Text(item.title)
                    .font(.largeTitle)
                    .fontWeight(.heavy)
                    .foregroundStyle(Color.white)
                Text(item.description_)
                    .font(.callout)
                    .foregroundStyle(Color.white)
            }
        }
        .padding(16)
    }
}

#Preview {
    OnboardingPage(item: .welcome)
}
