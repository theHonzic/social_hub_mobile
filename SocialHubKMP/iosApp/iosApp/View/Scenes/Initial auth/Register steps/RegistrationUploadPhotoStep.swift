//
//  RegistrationUploadPhotoStep.swift
//  iosApp
//
//  Created by Jan Janovec on 29.11.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RegistrationUploadPhotoStep: View {
    var body: some View {
        VStack(alignment: .center, spacing: 14) {
            HStack {
                VStack(alignment: .leading, spacing: 20) {
                    Text("Let's get to know each other")
                        .font(.largeTitle)
                        .fontWeight(.heavy)
                        .foregroundStyle(Color.white)
                        .padding(.top, 30)
                    Text("Tell us little bit more.")
                        .foregroundStyle(Color.gray)
                }
                Spacer()
            }
            PhotoPickerView()
            Spacer()
        }
    }
}

#Preview {
    RegistrationUploadPhotoStep()
}
