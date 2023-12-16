//
//  AppLoadingView.swift
//  iosApp
//
//  Created by Jan Janovec on 12.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AppLoadingView: View {
    @EnvironmentObject private var globalSettings: GlobalSettingsVM
    var body: some View {
        ZStack {
            Color("PrimaryColor")
            VStack {
                Spacer()
                Text("Social hub")
                    .font(.largeTitle)
                    .fontWeight(.bold)
                Spacer()
            }
        }
        .ignoresSafeArea()
    }
}

#Preview {
    AppLoadingView()
}
